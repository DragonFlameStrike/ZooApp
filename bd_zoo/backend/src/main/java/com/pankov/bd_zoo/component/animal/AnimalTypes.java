package com.pankov.bd_zoo.component.animal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnimalTypes {
    public enum PredatorType {
        LION("Лев", true),
        TIGER("Тигр", true),
        POLAR_BEAR("Белый Медведь", false),
        BROWN_BEAR("Бурый Медведь", false),
        WOLF("Волк", false);

        private final String name;
        private final boolean heatNeeded;

        PredatorType(String name, boolean heatNeeded) {
            this.name = name;
            this.heatNeeded = heatNeeded;
        }

        public String getName() {
            return name;
        }

        public boolean isHeatNeeded() {
            return heatNeeded;
        }
    }

    public enum HerbivoreType {
        OSTRICH("Страус", true),
        PARROT("Попугай", true);

        private final String name;
        private final boolean heatNeeded;

        HerbivoreType(String name, boolean heatNeeded) {
            this.name = name;
            this.heatNeeded = heatNeeded;
        }

        public String getName() {
            return name;
        }

        public boolean isHeatNeeded() {
            return heatNeeded;
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
    public static boolean getIsHeatNeeded(Enum<?> e) {
        try {
            Method m = e.getClass().getMethod("isHeatNeeded");
            return (boolean) m.invoke(e);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalArgumentException("Invalid enum value " + e, ex);
        }
    }
}
