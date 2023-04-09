package com.pankov.bd_zoo.component.supplier;

import com.pankov.bd_zoo.component.food.Food;
import com.pankov.bd_zoo.component.food.FoodRepository;
import com.pankov.bd_zoo.component.food.FoodTypeRus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SupplierFactory {
    private final FoodRepository foodRepository;

    @Autowired
    public SupplierFactory(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Supplier> create(FoodTypeRus type) {
        List<Supplier> suppliers = new ArrayList<>();
        Food food = foodRepository.findByType(type.name());
        int pricePerFood = ThreadLocalRandom.current().nextInt(100, 1000);
        int startCount = 1;
        for (int i = 1; i <= 3; i++) {
            int count = startCount;
            int price = (int) (pricePerFood*count*Math.pow(0.98,count));

            Supplier supplier = new Supplier();
            supplier.setType(type.name());
            supplier.setCount(count);
            supplier.setPrice(price);
            supplier.setFood(food);

            suppliers.add(supplier);
            startCount+=5;
        }

        return suppliers;
    }
}
