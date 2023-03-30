package com.pankov.bd_zoo.component.cage;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CagesService implements ICagesService {

    @Autowired
    private final CagesRepository repository;

    @Override
    public List<Cage> findAll() {
        return repository.findAll();
    }

    @Override
    public Cage findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cage with id " + id + " not found"));
    }

    @Override
    public Cage create(Cage cage) {
        List<Cage> allCages = repository.findAll();
        if (allCages.size() < 6) {
            return repository.save(cage);
        } else {
            throw new RuntimeException("Cannot create cage. Maximum number of cages reached.");
        }
    }

    @Override
    public Cage update(Cage cage) {
        Cage existingCage = findById(cage.getId());
        existingCage.setNumber(cage.getNumber());
        return repository.save(existingCage);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
