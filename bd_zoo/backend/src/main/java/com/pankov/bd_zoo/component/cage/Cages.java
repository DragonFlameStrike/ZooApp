package com.pankov.bd_zoo.component.cage;


import jakarta.persistence.*;


@Entity
@Table(name = "cages")
public class Cages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;

    public Cages() {
    }

    public Cages(String number) {
        this.number = number;
    }
}
