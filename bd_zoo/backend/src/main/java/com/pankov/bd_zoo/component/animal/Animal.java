package com.pankov.bd_zoo.component.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.cage.Cage;
import com.pankov.bd_zoo.component.worker.Worker;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "heat_needed")
    private Boolean heatNeeded;

    @Column(name = "relocation_needed")
    private Boolean relocationNeeded;

    @Column(name = "physcondition_normally")
    private Boolean physConditionNormally;

    @Column(name = "mother_id")
    private Long motherId;

    @Column(name = "father_id")
    private Long fatherId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cage_id")
    private Cage cage;

    @JsonIgnore
    @ManyToMany(mappedBy = "animals")
    private Set<Worker> workers = new HashSet<>();
}
