package com.pankov.bd_zoo.component.kitchen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pankov.bd_zoo.component.animal.Animal;
import com.pankov.bd_zoo.component.food.Food;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "feed")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "feed_food",
            joinColumns = @JoinColumn(name = "feed_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private Set<Food> foods;

    @Column(name = "feed_type", nullable = false)
    private String feedType;

    @Column(name = "feeding_per_week", nullable = false)
    private Integer feedingPerWeek;

    @Column(name = "season", nullable = false)
    private String season;
}
