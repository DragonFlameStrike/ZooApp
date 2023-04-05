package com.pankov.bd_zoo.component.worker;

import com.pankov.bd_zoo.component.animal.Animal;
import lombok.Data;

import java.util.Set;


@Data
public class WorkerDto {
    private Worker worker;
    private Set<String> currAnimalsId;
}

