package com.pankov.bd_zoo.component.hospital.illness;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class IllnessFactory {
    private static final IllnessTypes[] TYPES = IllnessTypes.values();
    private static final Random random = new Random();

    public Illness createIllness() {
        Illness illness = new Illness();
        illness.setType(TYPES[random.nextInt(TYPES.length)].getName());
        illness.setDate(LocalDate.now());
        return illness;
    }
}