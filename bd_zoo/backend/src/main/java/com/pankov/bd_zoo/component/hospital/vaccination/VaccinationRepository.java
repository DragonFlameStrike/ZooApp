package com.pankov.bd_zoo.component.hospital.vaccination;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRepository  extends JpaRepository<Vaccination, Long> {
    List<Vaccination> findAllByAnimalId(Long id);
}
