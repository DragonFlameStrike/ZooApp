package com.pankov.bd_zoo.component.worker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT w FROM Animal a JOIN a.workers w WHERE a.cage.id = :cageId")
    Set<Worker> findAllWorkersByCageId(@Param("cageId") Long cageId);
}
