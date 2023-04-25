package com.pankov.bd_zoo.component.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FoodService implements IFoodService {
    private final FoodRepository repository;

    @Autowired
    public FoodService(FoodRepository repository) {
        this.repository = repository;
    }

    @Override
    public Food create(Food food) {
        return repository.save(food);
    }

    @Override
    public Food findById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Food with id " + id + " not found"));
    }

    @Override
    public Food update(Food food) {
        Food existingFood = findById(food.getId());
        existingFood.setType(food.getType());
        existingFood.setCount(food.getCount());
        return repository.save(existingFood);
    }

    @Override
    public void deleteById(Long id){
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public Page<Food> findAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public List<Food> findAll() {
        return repository.findAll();
    }

    @Override
    public void addFoodCount(Long id, int count) {
        Food food = findById(id);
        food.setCount(food.getCount() + count);
        repository.save(food);
    }

    @Override
    public void subtractFoodCount(Long id, int count) {
        Food food = findById(id);
        if (food.getCount() >= count) {
            food.setCount(food.getCount() - count);
            repository.save(food);
        } else {
            throw new IllegalArgumentException("Not enough food in stock");
        }
    }

    @Override
    public Food findByType(String type) {
        return repository.findByType(type);
    }
}

