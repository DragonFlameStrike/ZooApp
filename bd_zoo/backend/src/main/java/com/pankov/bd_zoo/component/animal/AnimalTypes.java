package com.pankov.bd_zoo.component.animal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnimalTypes {
    public enum PredatorType {
        LION("Лев"),
        TIGER("Тигр"),
        POLAR_BEAR("Белый Медведь"),
        BROWN_BEAR("Бурый Медведь"),
        WOLF("Волк");

        private final String name;

        PredatorType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum HerbivoreType {
        OSTRICH("Страус"),
        PARROT("Попугай");

        private final String name;

        HerbivoreType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    public static String getName(Enum<?> e) {
        try {
            Method m = e.getClass().getMethod("getName");
            return (String) m.invoke(e);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalArgumentException("Invalid enum value " + e, ex);
        }
    }
}
