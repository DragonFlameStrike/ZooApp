package com.pankov.bd_zoo.component.animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.cage.Cage;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cage_id")
    private Cage cage;
}
