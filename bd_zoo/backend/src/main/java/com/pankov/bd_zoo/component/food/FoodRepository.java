package com.pankov.bd_zoo.component.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("SELECT f FROM Food f WHERE f.type = :name")
    Food findByType(@Param("name") String name);
}