package com.pankov.bd_zoo.component.hospital.vaccination;

import java.util.List;

public interface IVaccinationService {

    Vaccination createVaccination(Vaccination vaccination);

    void deleteVaccinationById(Long id);

    List<String> getAllVaccinationsTypes();
}