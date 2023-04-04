package com.pankov.bd_zoo.component.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;
    private final WorkerPool workerPool;

    @Autowired
    public WorkerController(WorkerService workerService, WorkerPool workerPool) {
        this.workerService = workerService;
        this.workerPool = workerPool;
    }

    @GetMapping("/")
    public List<Worker> getAll() {
        return workerService.findAll();
    }

    @GetMapping("/{id}")
    public Worker getById(@PathVariable Long id) {
        return workerService.findById(id);
    }

    @PostMapping("/")
    public Worker create(@RequestBody Worker worker) {
        return workerService.create(worker);
    }

    @PutMapping("/{id}")
    public Worker update(@PathVariable Long id, @RequestBody Worker worker) {
        worker.setId(id);
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
