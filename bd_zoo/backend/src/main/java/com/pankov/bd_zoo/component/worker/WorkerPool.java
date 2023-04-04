package com.pankov.bd_zoo.component.worker;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkerPool {

    private final List<Worker> pool = new ArrayList<>();

    private final int maxPoolSize = 3;

    @Autowired
    private WorkerFactory workerFactory;


    @PostConstruct
    public void init() {
        while(this.pool.size()<maxPoolSize) {
            createWorker();
        }
    }

    public Worker getWorkerByIndex(int index){
        return pool.get(index);
    }

    public void createWorker() {
        if (pool.size() < maxPoolSize) {
            pool.add(workerFactory.createWorker());
        } else {
            throw new IllegalStateException("Worker pool is full. Cannot add more workers.");
        }
    }

    public void removeWorker(int index) {
        if (pool.isEmpty()) {
            throw new IllegalStateException("Worker pool is empty. Cannot remove any worker.");
        } else {
            pool.remove(index);
            createWorker();
        }
    }
}