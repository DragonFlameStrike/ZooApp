package com.pankov.bd_zoo.component.hospital.illness;

public enum IllnessTypes {
    COLD("Простуда"),
    FEVER("Лихорадка"),
    PNEUMONIA("Пневмония"),
    INFECTION("Инфекция"),
    INDIGESTION("Нарушение пищеварения");

    private final String name;

    IllnessTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
