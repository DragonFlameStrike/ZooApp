package com.pankov.bd_zoo.component.cage;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "cages")
@Data
public class Cage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
}
