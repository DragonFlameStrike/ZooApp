package com.pankov.bd_zoo.component.delivery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IDeliveryService {
    Page<DeliveryDto> findAll(PageRequest id);
    Delivery findById(Long id);
    Delivery create(Delivery delivery);
    Delivery update(Delivery delivery);
    void deleteById(Long id);
}