package com.pankov.bd_zoo.component.cage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/Cages")
public class CagesController {
    @Autowired
    private CagesRepository cagesRepository;

    @GetMapping("/")
    public List<Cages> getAll() {
        return cagesRepository.findAll();
    }
}
