package com.pankov.bd_zoo.component.animal;

import com.pankov.bd_zoo.component.animal.dto.AnimalWithIllnessDto;
import com.pankov.bd_zoo.component.hospital.VaccinesForIllnesses;
import com.pankov.bd_zoo.component.hospital.illness.Illness;
import com.pankov.bd_zoo.component.hospital.illness.IllnessRepository;
import com.pankov.bd_zoo.component.hospital.vaccination.Vaccination;
import com.pankov.bd_zoo.component.hospital.vaccination.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AnimalService implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final IllnessRepository illnessRepository;
    private final VaccinationRepository vaccinationRepository;
    private final VaccinesForIllnesses vaccinesForIllnesses;


    @Autowired
    public AnimalService(AnimalRepository animalRepository, IllnessRepository illnessRepository, VaccinationRepository vaccinationRepository,VaccinesForIllnesses vaccinesForIllnesses) {
        this.animalRepository = animalRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.illnessRepository = illnessRepository;
        this.vaccinesForIllnesses = vaccinesForIllnesses;
    }

    @Override
    public Animal create(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal findById(Long id) throws NoSuchElementException {
        return animalRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Animal with id " + id + " not found"));
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
        return animalRepository.save(existingAnimal);
    }

    @Override
    public void deleteById(Long id){
        animalRepository.deleteById(id);
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
        return animalRepository.findAllByCageId(id);
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
    public Page<AnimalWithIllnessDto> findAllAnimalWithIllness(Pageable pageable) {
        List<Animal> animals = animalRepository.findAllWithIllness();
        List<AnimalWithIllnessDto> animalWithIllnessDtos = new ArrayList<>();

        for (Animal animal : animals) {
            List<Illness> illnesses = illnessRepository.findAllByAnimalId(animal.getId());
            List<Vaccination> vaccinations = vaccinationRepository.findAllByAnimalId(animal.getId());

            for (Vaccination vaccination: vaccinations) {
                for (Illness illness : new ArrayList<>(illnesses)) {
                    String vaccineForIllness = vaccinesForIllnesses.get(illness.getType());
                    if (vaccineForIllness != null && vaccineForIllness.equals(vaccination.getType())) {
                        illnesses.remove(illness);
                    }
                }
            }
            if(!illnesses.isEmpty()) {
                AnimalWithIllnessDto animalWithIllnessDto = new AnimalWithIllnessDto();
                animalWithIllnessDto.setAnimal(animal);
                animalWithIllnessDto.setIllnesses(illnesses);
                animalWithIllnessDtos.add(animalWithIllnessDto);
            }
        }

        return new PageImpl<>(animalWithIllnessDtos, pageable, animals.size());
    }


    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }
}
