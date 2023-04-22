package com.pankov.bd_zoo.component.animal;

import com.pankov.bd_zoo.component.animal.dto.AnimalWithIllnessDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAnimalService {
    Page<AnimalWithIllnessDto> findAllAnimalWithIllness(Pageable pageable);

    List<Animal> findAll();
    Animal findById(Long id);
    Animal create(Animal animal);
    Animal update(Animal animal);
    void deleteById(Long id);
    Integer findCageNumberById(Long id);

    List<Animal> findAllByCageId(Long id);
    void checkForRelocation(List<Animal> cage);
}

