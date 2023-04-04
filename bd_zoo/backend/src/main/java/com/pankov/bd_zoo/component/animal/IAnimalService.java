package com.pankov.bd_zoo.component.animal;

import java.util.List;

public interface IAnimalService {
    List<Animal> findAll();
    Animal findById(Long id);
    Animal create(Animal animal);
    Animal update(Animal animal);
    void deleteById(Long id);
}

