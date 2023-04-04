package com.pankov.bd_zoo.component.animal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnimalPool {

    private final List<Animal> pool = new ArrayList<>();

    private final int maxPoolSize = 3;

    @Autowired
    private AnimalFactory animalFactory;


    @PostConstruct
    public void init() {
        while(this.pool.size()<maxPoolSize) {
            createAnimal();
        }
    }

    public Animal getAnimalByIndex(int index){
        return pool.get(index);
    }

    public void createAnimal() {
        if (pool.size() < maxPoolSize) {
            pool.add(animalFactory.createAnimal());
        } else {
            throw new IllegalStateException("Animal pool is full. Cannot add more animals.");
        }
    }

    public void removeAnimal(int index) {
        if (pool.isEmpty()) {
            throw new IllegalStateException("Animal pool is empty. Cannot remove any animal.");
        } else {
            pool.remove(index);
            createAnimal();
        }
    }
}
