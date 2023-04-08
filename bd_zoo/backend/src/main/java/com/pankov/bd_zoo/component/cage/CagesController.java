package com.pankov.bd_zoo.component.cage;


import com.pankov.bd_zoo.component.animal.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cages")
public class CagesController {

    private final CagesService cagesService;

    @Autowired
    public CagesController(CagesService cagesService) {
        this.cagesService = cagesService;
    }

    @GetMapping("/")
    public List<Cage> getAll() {
        return cagesService.findAll();
    }


    @GetMapping("/{id}")
    public Cage getById(@PathVariable Long id) {
        return cagesService.findById(id);
    }

    @PostMapping("/")
    public Cage create(@RequestBody Cage cage) {
        return cagesService.create(cage);
    }

    @PutMapping("/{id}")
    public Cage update(@PathVariable Long id, @RequestBody Cage cage) {
        cage.setId(id);
        return cagesService.update(cage);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cagesService.deleteById(id);
    }
}
