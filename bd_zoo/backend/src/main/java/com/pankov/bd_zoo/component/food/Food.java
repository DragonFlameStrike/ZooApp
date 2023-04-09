package com.pankov.bd_zoo.component.food;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "food")
@Data
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "count", nullable = false)
    private Integer count;
}

