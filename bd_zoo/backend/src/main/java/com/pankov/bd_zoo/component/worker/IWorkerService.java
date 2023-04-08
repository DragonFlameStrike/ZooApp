package com.pankov.bd_zoo.component.worker;

import com.pankov.bd_zoo.component.animal.Animal;

import java.util.List;
import java.util.Set;

public interface IWorkerService {
    List<Worker> findAll();
    Worker findById(Long id);
    Worker create(Worker worker);
    Worker update(Worker worker);
    void deleteById(Long id);
    Set<String> findAnimalsIdByWorkerId(Long id);

    Set<Worker> getAllWorkersByCageId(Long id);
}
