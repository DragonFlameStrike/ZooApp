package com.pankov.bd_zoo.component.hospital.vaccination;

public enum VaccinationTypes {
    ANTI_COLD("Нечихаин"),
    ANTI_FEVER("Нелихародин"),
    ANTI_PNEUMONIA("Антипневмонин"),
    ANTI_INFECTION("Антидот"),
    ANTI_INDIGESTION("Уголь черный, активированный");

    private final String name;

    VaccinationTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
