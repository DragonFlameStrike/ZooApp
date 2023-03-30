package com.pankov.bd_zoo.component.cage;

import java.util.List;

public interface ICagesService {
    List<Cage> findAll();
    Cage findById(Long id);
    Cage create(Cage cage);
    Cage update(Cage cage);
    void deleteById(Long id);
}