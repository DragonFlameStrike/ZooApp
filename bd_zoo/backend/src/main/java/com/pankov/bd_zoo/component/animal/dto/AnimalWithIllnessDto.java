package com.pankov.bd_zoo.component.animal.dto;

import com.pankov.bd_zoo.component.animal.Animal;
import com.pankov.bd_zoo.component.hospital.illness.Illness;
import lombok.Data;

import java.util.List;

@Data
public class AnimalWithIllnessDto {
    Animal animal;
    List<Illness> illnesses;
}
