package com.pankov.bd_zoo.component.supplier;

import com.pankov.bd_zoo.component.food.Food;

import java.util.List;

public interface ISupplierService {
    List<Supplier> findAll();
    Supplier findById(Long id);
    Supplier create(Supplier food);
}
