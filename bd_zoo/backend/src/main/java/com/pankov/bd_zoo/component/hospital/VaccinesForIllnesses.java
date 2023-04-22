package com.pankov.bd_zoo.component.hospital;

import com.pankov.bd_zoo.component.hospital.illness.IllnessTypes;
import com.pankov.bd_zoo.component.hospital.vaccination.VaccinationTypes;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class VaccinesForIllnesses {
    public Map<String, String> vaccinesForIllnesses;

    public VaccinesForIllnesses() {
        vaccinesForIllnesses = new HashMap<>();
        vaccinesForIllnesses.put(IllnessTypes.COLD.getName(), VaccinationTypes.ANTI_COLD.getName());
        vaccinesForIllnesses.put(IllnessTypes.FEVER.getName(), VaccinationTypes.ANTI_FEVER.getName());
        vaccinesForIllnesses.put(IllnessTypes.PNEUMONIA.getName(), VaccinationTypes.ANTI_PNEUMONIA.getName());
        vaccinesForIllnesses.put(IllnessTypes.INFECTION.getName(), VaccinationTypes.ANTI_INFECTION.getName());
        vaccinesForIllnesses.put(IllnessTypes.INDIGESTION.getName(), VaccinationTypes.ANTI_INDIGESTION.getName());
    }


    public String get(String type) {
        return this.vaccinesForIllnesses.get(type);
    }
}
