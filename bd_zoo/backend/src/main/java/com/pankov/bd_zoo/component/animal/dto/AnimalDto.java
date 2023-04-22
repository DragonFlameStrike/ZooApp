package com.pankov.bd_zoo.component.animal.dto;

import com.pankov.bd_zoo.component.animal.Animal;
import lombok.Data;

@Data
public class AnimalDto {
    private Animal animal;
    private String cage;
}
