package com.pankov.bd_zoo.component.animal;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

@Component
public class AnimalFactory {
    private static final AnimalTypes.PredatorType[] PREDATOR_TYPES = AnimalTypes.PredatorType.values();
    private static final AnimalTypes.HerbivoreType[] HERBIVORE_TYPES = AnimalTypes.HerbivoreType.values();
    private static final Enum[] TYPES = Stream.concat(Arrays.stream(PREDATOR_TYPES), Arrays.stream(HERBIVORE_TYPES))
            .toArray(Enum[]::new);


    private static final String[] MALE_NAMES = {"Симба", "Лео", "Джек", "Макс", "Антон"};
    private static final String[] FEMALE_NAMES = {"Нэля", "Луна", "Умка", "Белла", "Айва"};
    private static final String[] SEXES = {"м", "ж"};

    private static final Random random = new Random();

    public Animal createAnimal() {
        Animal animal = new Animal();
        Enum type = TYPES[random.nextInt(TYPES.length)];
        String nameType = AnimalTypes.getName(type);
        animal.setType(nameType);
        String name;
        String sex;
        if (SEXES[random.nextInt(SEXES.length)].equals("м")) {
            name = MALE_NAMES[random.nextInt(MALE_NAMES.length)];
            sex = "м";
        } else {
            name = FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
            sex = "ж";
        }
        animal.setName(name);
        animal.setSex(sex);
        animal.setWeight(random.nextInt(100) + 50);
        animal.setHeight(random.nextInt(200) + 50);
        animal.setBirthday(LocalDate.of(2015 + random.nextInt(6), 1 + random.nextInt(12), 1 + random.nextInt(28)));
        return animal;
    }
}
