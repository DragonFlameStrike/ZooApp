package com.pankov.bd_zoo.component.hospital.vaccination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.animal.Animal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
@Data
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String type;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
