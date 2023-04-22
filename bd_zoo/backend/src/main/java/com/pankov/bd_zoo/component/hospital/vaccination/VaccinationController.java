package com.pankov.bd_zoo.component.hospital.vaccination;

import com.pankov.bd_zoo.component.animal.Animal;
import com.pankov.bd_zoo.component.animal.AnimalController;
import com.pankov.bd_zoo.component.animal.dto.AnimalDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {

    private final AnimalController animalController;
    private final VaccinationService vaccinationService;

    public VaccinationController(VaccinationService vaccinationService,AnimalController animalController){
        this.vaccinationService =vaccinationService;
        this.animalController = animalController;
    }

    @GetMapping("/")
    public List<String> getAll() {
        return vaccinationService.getAllVaccinationsTypes();
    }

    @PostMapping("/{id}")
    public void vaccinateAnimal(@PathVariable Long id, @RequestBody Map<String, List<String>> requestBody) {
        List<String> vaccineTypes = requestBody.get("vaccinations");
        for (String vaccineType: vaccineTypes) {
            Vaccination vaccination = new Vaccination();
            vaccination.setType(vaccineType);
            vaccination.setAnimal(animalController.getById(id));
            vaccination.setDate(LocalDate.now());
            vaccinationService.createVaccination(vaccination);
        }
    }
}
