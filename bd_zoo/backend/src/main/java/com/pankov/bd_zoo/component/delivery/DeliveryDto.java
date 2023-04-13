package com.pankov.bd_zoo.component.delivery;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeliveryDto {
    private Integer count;
    private Integer price;
    private String foodType;
    private LocalDate date;
}
