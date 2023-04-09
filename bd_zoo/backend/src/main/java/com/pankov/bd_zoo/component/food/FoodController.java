package com.pankov.bd_zoo.component.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Zoo/food/")
public class FoodController {

    private final IFoodService foodService;

    @Autowired
    public FoodController(IFoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/")
    public List<Food> getAll() {
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

    @PostMapping("/{id}/add/{count}")
    public void addFoodCount(@PathVariable Long id, @PathVariable Integer count) {
        Food food = foodService.findById(id);
        food.setCount(food.getCount() + count);
        foodService.update(food);
    }

    @PostMapping("/{id}/remove/{count}")
    public void removeFoodCount(@PathVariable Long id, @PathVariable Integer count) {
        Food food = foodService.findById(id);
        food.setCount(food.getCount() - count);
        foodService.update(food);
    }
}

