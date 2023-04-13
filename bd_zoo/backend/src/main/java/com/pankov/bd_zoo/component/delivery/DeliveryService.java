package com.pankov.bd_zoo.component.delivery;

import com.pankov.bd_zoo.component.food.FoodController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService implements IDeliveryService {

    private final DeliveryRepository deliveryRepository;


    @Autowired
    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Page<DeliveryDto> findAll(PageRequest pageRequest) {
        Page<Delivery> deliveryPage = deliveryRepository.findAll(pageRequest);
        List<DeliveryDto> deliveryDtos = new ArrayList<>();
        for (Delivery delivery : deliveryPage.getContent()) {
            DeliveryDto dto = new DeliveryDto();
            dto.setCount(delivery.getCount());
            dto.setPrice(delivery.getPrice());
            dto.setDate(delivery.getDate());
            dto.setFoodType(delivery.getFood().getType());
            deliveryDtos.add(dto);
        }
        return new PageImpl<>(deliveryDtos, pageRequest, deliveryPage.getTotalElements());
    }

    @Override
    public Delivery findById(Long id) {
        return deliveryRepository.findById(id).orElse(null);
    }

    @Override
    public Delivery create(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Delivery update(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public void deleteById(Long id) {
        deliveryRepository.deleteById(id);
    }
}