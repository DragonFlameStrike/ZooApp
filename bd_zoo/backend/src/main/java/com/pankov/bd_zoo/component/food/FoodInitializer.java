package com.pankov.bd_zoo.component.food;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FoodInitializer {
    @Autowired
    private FoodRepository foodRepository;

    @PostConstruct
    public void init() {
        if(foodRepository.count() ==  0) {
            for (FoodTypeRus type : FoodTypeRus.values()) {
                Food food = new Food();
                food.setType(String.valueOf(type));
                food.setCount(0);
                foodRepository.save(food);
            }
        }
    }
}