package com.pankov.bd_zoo.component.kitchen.dto;

import com.pankov.bd_zoo.component.kitchen.Feed;
import lombok.Data;

import java.util.List;

@Data
public class FeedGetDto {
    String animalName;
    List<String> foodTypes;
    Feed feed;
}
