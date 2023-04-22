package com.pankov.bd_zoo.component.animal;

import com.pankov.bd_zoo.component.hospital.illness.Illness;
import com.pankov.bd_zoo.component.hospital.illness.IllnessFactory;
import com.pankov.bd_zoo.component.hospital.illness.IllnessRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalEventService {

    private final AnimalRepository animalRepository;
    private final IllnessRepository illnessRepository;
    private final IllnessFactory illnessFactory;

    public AnimalEventService(AnimalRepository animalRepository, IllnessRepository illnessRepository, IllnessFactory illnessFactory) {
        this.animalRepository = animalRepository;
        this.illnessFactory = illnessFactory;
        this.illnessRepository = illnessRepository;
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
        // реализация метода
    }
}