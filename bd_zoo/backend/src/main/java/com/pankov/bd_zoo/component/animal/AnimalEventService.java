package com.pankov.bd_zoo.component.animal;

import com.pankov.bd_zoo.component.hospital.illness.Illness;
import com.pankov.bd_zoo.component.hospital.illness.IllnessFactory;
import com.pankov.bd_zoo.component.hospital.illness.IllnessRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalEventService {

    private final AnimalRepository animalRepository;
    private final IllnessRepository illnessRepository;
    private final IllnessFactory illnessFactory;
    private final AnimalFactory animalFactory;

    public AnimalEventService(AnimalRepository animalRepository, IllnessRepository illnessRepository, IllnessFactory illnessFactory, AnimalFactory animalFactory) {
        this.animalRepository = animalRepository;
        this.illnessFactory = illnessFactory;
        this.illnessRepository = illnessRepository;
        this.animalFactory = animalFactory;
    }

    @Scheduled(fixedDelay = 60000)
    public void randomIllnessCreateEvent() {
        long count = animalRepository.count();
        boolean isIllnessNotExist = true;
        if (count > 0) {
            long randomId = (long) (Math.random() * count) + 1;
            Optional<Animal> animalOptional = animalRepository.findById(randomId);
            if (animalOptional.isPresent()) {
                Animal animal = animalOptional.get();
                Illness illness = illnessFactory.createIllness();
                List<Illness> animalIllnesses = illnessRepository.findIllnessByAnimalId(animal.getId());
                for (Illness animalIllness : animalIllnesses) {
                    if (animalIllness.getType().equals(illness.getType())) {
                        isIllnessNotExist = false;
                        break;
                    }
                }
                if (isIllnessNotExist) {
                    illness.setAnimal(animal);
                    illnessRepository.save(illness);
                }

            }
        }
    }
    @Scheduled(fixedDelay = 120000) // запуск каждые две минуты
    public void checkForBreeding() {
        List<Animal> animals = animalRepository.findAll();
        List<Animal> potentialParents = new ArrayList<>();

        // Отбираем всех животных, у которых возраст больше года и они находятся в одной клетке
        for (Animal animal : animals) {
            if (animal.getBirthday().isBefore(LocalDate.now().minusYears(1)) && animal.getCage() != null) {
                potentialParents.add(animal);
            }
        }

        // Проверяем возможность размножения между каждыми двумя животными
        boolean done = false;
        for (int i = 0; i < potentialParents.size(); i++) {
            Animal parent1 = potentialParents.get(i);
            if(done) break;
            for (int j = i + 1; j < potentialParents.size(); j++) {
                Animal parent2 = potentialParents.get(j);

                // Проверяем, что оба родителя одного типа, разных полов и находятся в одной клетке
                if (parent1.getType().equals(parent2.getType()) &&
                        !parent1.getSex().equals(parent2.getSex()) &&
                        parent1.getCage().getNumber().equals(parent2.getCage().getNumber())) {
                    boolean isReadyForBreeding = true;
                    Animal mother;
                    Animal father;
                    if (parent1.getSex().equals("ж")) {
                        mother = parent1;
                        father = parent2;
                    } else {
                        father = parent1;
                        mother = parent2;
                    }
                    // Проверяем, что у самки нет детей младше полугода
                    List<Animal> offspring = animalRepository.findAllByMotherId(mother.getId());
                    for (Animal child : offspring) {
                        if (child.getBirthday().isAfter(LocalDate.now().minusMonths(6))) {
                            isReadyForBreeding = false;
                            break;
                        }
                    }

                    // Если все условия выполнены, создаем новое животное и сохраняем его в базу данных
                    if (isReadyForBreeding) {
                        animalRepository.save(animalFactory.createAnimalFromParents(mother,father));
                        done = true;
                        break;
                    }
                }
            }
        }
    }

}