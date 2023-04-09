package com.pankov.bd_zoo.component.supplier;

import com.pankov.bd_zoo.component.food.Food;
import com.pankov.bd_zoo.component.food.FoodTypeRus;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDataInitializer {

    private final SupplierRepository supplierRepository;
    private final SupplierFactory supplierFactory;

    @Autowired
    public SupplierDataInitializer(SupplierRepository supplierRepository,SupplierFactory supplierFactory) {
        this.supplierRepository = supplierRepository;
        this.supplierFactory = supplierFactory;
    }

    @PostConstruct
    public void init() {
        if (supplierRepository.count() == 0) {
            List<Supplier> suppliers = new ArrayList<>();

            for (FoodTypeRus type : FoodTypeRus.values()) {
                suppliers.addAll(supplierFactory.create(type));
            }

            supplierRepository.saveAll(suppliers);
        }
    }
}

