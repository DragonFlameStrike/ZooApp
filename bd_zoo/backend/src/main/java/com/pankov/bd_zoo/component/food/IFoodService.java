package com.pankov.bd_zoo.component.food;

import java.util.List;

public interface IFoodService {
    List<Food> findAll();
    Food findById(Long id);
    Food create(Food food);
    Food update(Food food);
    void deleteById(Long id);

    // метод для добавления количества еды по id
    void addFoodCount(Long id, int count);

    // метод для отнимания количества еды по id
    void subtractFoodCount(Long id, int count);
}
