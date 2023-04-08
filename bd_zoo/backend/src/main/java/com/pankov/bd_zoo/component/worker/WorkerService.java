package com.pankov.bd_zoo.component.worker;

import com.pankov.bd_zoo.component.animal.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class WorkerService implements IWorkerService {

    private final WorkerRepository repository;

    @Autowired
    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Worker create(Worker worker) {
        return repository.save(worker);
    }

    @Override
    public Worker findById(Long id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Worker with id " + id + " not found"));
    }

    @Override
    public Worker update(Worker worker) {
        Worker existingWorker = findById(worker.getId());
        existingWorker.setName(worker.getName());
        existingWorker.setSex(worker.getSex());
        existingWorker.setProfession(worker.getProfession());
        existingWorker.setBirthday(worker.getBirthday());
        existingWorker.setHireDate(worker.getHireDate());
        existingWorker.setPriorService(worker.getPriorService());
        existingWorker.setSalary(worker.getSalary());
        existingWorker.setAnimals(worker.getAnimals());
        return repository.save(existingWorker);
    }

    @Override
    public void deleteById(Long id){
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public Set<String> findAnimalsIdByWorkerId(Long id) {
        Set<Animal> animals = findById(id).getAnimals();
        Set<String> animalsId = new HashSet<>();
        for (Animal animal: animals) {
            animalsId.add(animal.getId().toString());
        }
        return animalsId;
    }

    @Override
    public Set<Worker> getAllWorkersByCageId(Long id) {
        return repository.findAllWorkersByCageId(id);
    }

    @Override
    public List<Worker> findAll() {
        return repository.findAll();
    }
}
