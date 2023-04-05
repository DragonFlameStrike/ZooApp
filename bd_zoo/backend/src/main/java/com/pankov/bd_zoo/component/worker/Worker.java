package com.pankov.bd_zoo.component.worker;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.animal.Animal;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workers")
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "profession", nullable = false)
    private String profession;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "prior_service", nullable = false)
    private Double priorService;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "worker_animal",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id"))
    private Set<Animal> animals = new HashSet<>();
}