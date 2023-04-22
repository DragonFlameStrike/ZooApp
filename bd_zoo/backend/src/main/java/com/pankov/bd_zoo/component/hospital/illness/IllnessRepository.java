package com.pankov.bd_zoo.component.hospital.illness;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IllnessRepository extends JpaRepository<Illness, Long> {
    @Query("SELECT i FROM Illness i WHERE i.animal.id = :animalId")
    List<Illness> findIllnessByAnimalId(@Param("animalId") Long animalId);

    List<Illness> findAllByAnimalId(Long id);
}
