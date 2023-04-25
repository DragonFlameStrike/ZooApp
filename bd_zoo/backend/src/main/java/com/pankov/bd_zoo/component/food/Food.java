package com.pankov.bd_zoo.component.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.kitchen.Feed;
import com.pankov.bd_zoo.component.worker.Worker;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


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

    @JsonIgnore
    @ManyToMany(mappedBy = "foods")
    private Set<Feed> feeds = new HashSet<>();
}

