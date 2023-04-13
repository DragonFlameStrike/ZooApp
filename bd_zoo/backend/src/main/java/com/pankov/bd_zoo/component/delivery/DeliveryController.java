package com.pankov.bd_zoo.component.delivery;

import com.pankov.bd_zoo.component.food.Food;
import com.pankov.bd_zoo.component.food.FoodController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/deliveries/")
public class DeliveryController {

    private final IDeliveryService deliveryService;
    private final FoodController foodController;

    @Autowired
    public DeliveryController(IDeliveryService deliveryService, FoodController foodController) {
        this.deliveryService = deliveryService;
        this.foodController = foodController;
    }

    @GetMapping("")
    public Page<DeliveryDto> getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {

        return deliveryService.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "id"));

    }

    @GetMapping("/{id}")
    public Delivery getById(@PathVariable Long id) {
        return deliveryService.findById(id);
    }

    @PostMapping("")
    public Delivery create(@RequestBody DeliveryDto dto) {
        Delivery delivery = new Delivery();
        Food food = foodController.getFoodByType(dto.getFoodType());
        delivery.setCount(dto.getCount());
        delivery.setPrice(dto.getPrice());
        delivery.setFood(food);
        delivery.setDate(LocalDate.now());
        return deliveryService.create(delivery);
    }

    @PutMapping("/{id}")
    public Delivery update(@PathVariable Long id, @RequestBody Delivery delivery) {
        delivery.setId(id);
        return deliveryService.update(delivery);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        deliveryService.deleteById(id);
    }
}

