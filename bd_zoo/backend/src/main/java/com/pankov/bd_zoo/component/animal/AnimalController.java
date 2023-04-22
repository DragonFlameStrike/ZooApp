package com.pankov.bd_zoo.component.animal;

import com.pankov.bd_zoo.component.animal.dto.AnimalDto;
import com.pankov.bd_zoo.component.animal.dto.AnimalWithIllnessDto;
import com.pankov.bd_zoo.component.cage.Cage;
import com.pankov.bd_zoo.component.cage.CagesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final AnimalService animalService;
    private final AnimalPool animalPool;
    private final CagesController cagesController;

    @Autowired
    public AnimalController(AnimalService animalService, AnimalPool animalPool, CagesController cagesController) {
        this.animalService = animalService;
        this.animalPool = animalPool;
        this.cagesController = cagesController;
    }

    @GetMapping("/")
    public List<Animal> getAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public Animal getById(@PathVariable Long id) {
        return animalService.findById(id);
    }

    @GetMapping("/{id}/cage")
    public Integer getCageNumberByAnimalId(@PathVariable Long id) {
        return animalService.findCageNumberById(id);
    }

    @PostMapping("/")
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable Long id,  @RequestBody AnimalDto request) {
        Animal animal = request.getAnimal();
        Cage cage = cagesController.getById(Long.valueOf(request.getCage()));
        animal.setId(id);
        animal.setCage(cage);
        Animal out = animalService.update(animal);
        animalService.checkForRelocation(getAllAnimalInCage(cage.getId()));
        return out;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalService.deleteById(id);
    }

    @GetMapping("/pool/{index}")
    public Animal getAnimalFromPool(@PathVariable int index){
        return animalPool.getAnimalByIndex(index);
    }

    @PostMapping("/pool/{index}")
    public Animal createFromPool(@PathVariable int index){
        Animal animal = animalPool.getAnimalByIndex(index);
        animalPool.removeAnimal(index);
        return create(animal);
    }

    @GetMapping("/cage/{id}")
    public List<Animal> getAllAnimalInCage(@PathVariable Long id) {
        return animalService.findAllByCageId(id);
    }

    @GetMapping("/illness")
    public Page<AnimalWithIllnessDto> getAllAnimalWithIllness(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return animalService.findAllAnimalWithIllness(pageable);
    }
}
