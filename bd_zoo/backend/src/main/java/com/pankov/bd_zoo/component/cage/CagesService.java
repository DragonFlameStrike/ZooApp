package com.pankov.bd_zoo.component.cage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CagesService implements ICagesService{
    @Autowired
    private CagesRepository cagesRepository;

    @Override
    public List<Cages> getAllCages(){
        return cagesRepository.findAll();
    }
}
