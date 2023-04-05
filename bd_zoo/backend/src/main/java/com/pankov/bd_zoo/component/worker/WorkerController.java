package com.pankov.bd_zoo.component.worker;

import com.pankov.bd_zoo.component.animal.Animal;
import com.pankov.bd_zoo.component.animal.AnimalController;
import com.pankov.bd_zoo.component.animal.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerPool workerPool;
    private final AnimalController animalController;

    @Autowired
    public WorkerController(WorkerService workerService, WorkerPool workerPool, AnimalController animalController) {
        this.workerService = workerService;
        this.workerPool = workerPool;
        this.animalController = animalController;
    }

    @GetMapping("/")
    public List<Worker> getAll() {
        return workerService.findAll();
    }

    @GetMapping("/{id}")
    public Worker getById(@PathVariable Long id) {
        return workerService.findById(id);
    }

    @GetMapping("/{id}/animals")
    public Set<String> getWorkerAnimalsById(@PathVariable Long id) {
        return workerService.findAnimalsIdByWorkerId(id);
    }

    @PostMapping("/")
    public Worker create(@RequestBody Worker worker) {
        return workerService.create(worker);
    }

    @PutMapping("/{id}")
    public Worker update(@PathVariable Long id, @RequestBody WorkerDto dto) {
        Worker worker = dto.getWorker();
        worker.setId(id);
        Set<String> animalsId = dto.getCurrAnimalsId();
        Set<Animal> animals=new HashSet<>();
        for (String animalId: animalsId) {
            animals.add(animalController.getById(Long.valueOf(animalId)));
        }
        worker.setAnimals(animals);
        return workerService.update(worker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        workerService.deleteById(id);
    }

    @GetMapping("/pool/{index}")
    public Worker getWorkerFromPool(@PathVariable int index){
        return workerPool.getWorkerByIndex(index);
    }

    @PostMapping("/pool/{index}")
    public Worker createFromPool(@PathVariable int index){
        Worker worker = workerPool.getWorkerByIndex(index);
        workerPool.removeWorker(index);
        return create(worker);
    }
}
