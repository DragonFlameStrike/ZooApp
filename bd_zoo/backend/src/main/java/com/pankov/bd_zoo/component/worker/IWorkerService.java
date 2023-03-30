package com.pankov.bd_zoo.component.worker;

import java.util.List;

public interface IWorkerService {
    List<Worker> findAll();
    Worker findById(Long id);
    Worker create(Worker worker);
    Worker update(Worker worker);
    void deleteById(Long id);
}
