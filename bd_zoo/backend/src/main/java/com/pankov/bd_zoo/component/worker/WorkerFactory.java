package com.pankov.bd_zoo.component.worker;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class WorkerFactory {
    private static final String[] MALE_NAMES = {"Дмитрий", "Михаил", "Данил", "Никита", "Олег"};
    private static final String[] FEMALE_NAMES = {"Полина", "Анна", "Мария", "Александра", "Елена"};
    private static final String[] SURNAMES = {"Паньков", "Солодкин", "Курдюков", "Попов", "Калугин"};
    private static final String[] SEXES = {"м", "ж"};
    private static final String[] PROFESSIONS = {"Ветеринар", "Кормильщик", "Уборщик", "Надзиратель", "Разнорабочий"};

    private static final Random random = new Random();

    public  Worker createWorker() {
        String name, surname, sex;
        if (SEXES[random.nextInt(SEXES.length)].equals("м")) {
            name = MALE_NAMES[random.nextInt(MALE_NAMES.length)];
            surname = SURNAMES[random.nextInt(SURNAMES.length)];
            sex = "м";
        } else {
            name = FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)];
            surname = SURNAMES[random.nextInt(SURNAMES.length)] + "а";
            sex = "ж";
        }
        Worker worker = new Worker();
        worker.setName(name + " " + surname);
        worker.setSex(sex);
        worker.setProfession(PROFESSIONS[random.nextInt(PROFESSIONS.length)]);
        worker.setBirthday(LocalDate.of(1980 + random.nextInt(20), 1 + random.nextInt(12), 1 + random.nextInt(28)));
        worker.setHireDate(LocalDate.now());
        worker.setPriorService((double) Math.round(random.nextDouble() * 10 ));
        worker.setSalary((double) Math.round((20000.0 + random.nextDouble() * 50000.0) ));
        return worker;
    }
}