package com.pankov.bd_zoo.component.kitchen.dto;

import lombok.Data;

import java.util.List;

@Data
public class FeedCreateDto {
    private List<Long> foodsId;
    private Long animalId;
    private Integer feedingPerWeek;
    private String season;
}
