package com.pankov.bd_zoo.component.hospital.vaccination;


import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccinationService implements IVaccinationService {

    private final VaccinationRepository vaccinationRepository;

    public VaccinationService(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }

    @Override
    public Vaccination createVaccination(Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }

    @Override
    public void deleteVaccinationById(Long vaccinationId) {
        vaccinationRepository.deleteById(vaccinationId);
    }

    @Override
    public List<String> getAllVaccinationsTypes() {
        List<String> types = new ArrayList<>();
        for (VaccinationTypes vaccinationType : VaccinationTypes.values()) {
            types.add(vaccinationType.getName());
        }
        return types;
    }
}
