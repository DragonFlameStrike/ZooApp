package com.pankov.bd_zoo.component.supplier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.food.Food;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "suppliers")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String type;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "price", nullable = false)
    private Integer price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
