package com.pankov.bd_zoo.component.animal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimalService implements IAnimalService {

    private final AnimalRepository repository;

    @Autowired
    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Animal create(Animal animal) {
        return repository.save(animal);
    }

    @Override
    public Animal findById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Animal with id " + id + " not found"));
    }

    @Override
    public Animal update(Animal animal) {
        Animal existingAnimal = findById(animal.getId());
        existingAnimal.setName(animal.getName());
        existingAnimal.setType(animal.getType());
        existingAnimal.setSex(animal.getSex());
        existingAnimal.setWeight(animal.getWeight());
        existingAnimal.setHeight(animal.getHeight());
        existingAnimal.setBirthday(animal.getBirthday());
        existingAnimal.setHeatNeeded(animal.getHeatNeeded());
        existingAnimal.setRelocationNeeded(animal.getRelocationNeeded());
        existingAnimal.setPhysConditionNormally(animal.getPhysConditionNormally());
        existingAnimal.setCage(animal.getCage());
        return repository.save(existingAnimal);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Override
    public Integer findCageNumberById(Long id) {
        Animal animal = findById(id);
        if(animal.getCage() == null){
            return null;
        }
        return animal.getCage().getNumber();
    }

    @Override
    public List<Animal> findAllByCageId(Long id) {
        return repository.findAllByCageId(id);
    }

    @Override
    public void checkForRelocation(List<Animal> animals) {
        boolean predatorIsExist = false;
        for (Animal animal: animals) {
            for (AnimalTypes.PredatorType predatorType : AnimalTypes.PredatorType.values()) {
                if (AnimalTypes.getName(predatorType).equals(animal.getType())) {
                    predatorIsExist = true;
                    break;
                }
            }
            if(predatorIsExist) break;
        }
        for (Animal animal : animals) {
            if (predatorIsExist) {
                for (AnimalTypes.HerbivoreType herbivoreType : AnimalTypes.HerbivoreType.values()) {
                    animal.setRelocationNeeded(AnimalTypes.getName(herbivoreType).equals(animal.getType()));
                    update(animal);
                    break;
                }
            }
            else {
                animal.setRelocationNeeded(false);
                update(animal);
            }
        }
    }

    @Override
    public List<Animal> findAll() {
        return repository.findAll();
    }
}
