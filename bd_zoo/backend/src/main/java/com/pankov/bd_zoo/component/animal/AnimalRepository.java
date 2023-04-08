package com.pankov.bd_zoo.component.animal;

import com.pankov.bd_zoo.component.worker.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("SELECT animal FROM Animal animal WHERE animal.cage.id = :cageId")
    List<Animal> findAllByCageId(@Param("cageId") Long cageId);
}
