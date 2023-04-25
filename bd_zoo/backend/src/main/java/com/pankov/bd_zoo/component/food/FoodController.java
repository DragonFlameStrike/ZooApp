package com.pankov.bd_zoo.component.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

    private final IFoodService foodService;

    @Autowired
    public FoodController(IFoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/")
    public Page<Food> getAllInPage(@RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size) {
        return foodService.findAll(PageRequest.of(page, size, Sort.by("id")));
    }

    @GetMapping("/all")
    public List<Food> getAll(){
        return foodService.findAll();
    }


    @GetMapping("/{id}")
    public Food getById(@PathVariable Long id) {
        return foodService.findById(id);
    }

    @PostMapping("/")
    public Food create(@RequestBody Food food) {
        return foodService.create(food);
    }

    @PutMapping("/{id}")
    public Food update(@PathVariable Long id, @RequestBody Food food) {
        food.setId(id);
        return foodService.update(food);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        foodService.deleteById(id);
    }

    @PostMapping("/{type}/add/{count}")
    public void addFoodCount(@PathVariable String type, @PathVariable Integer count) {
        String decodedType = URLDecoder.decode(type, StandardCharsets.UTF_8);
        Food food = getFoodByType(decodedType);
        food.setCount(food.getCount() + count);
        foodService.update(food);
    }

    @PostMapping("/{type}/remove/{count}")
    public void removeFoodCount(@PathVariable String type, @PathVariable Integer count) {
        Food food = foodService.findByType(type);
        food.setCount(food.getCount() - count);
        foodService.update(food);
    }

    public Food getFoodByType(String type){
        return foodService.findByType(type);
    }
}

