package com.pankov.bd_zoo.component.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SupplierService implements ISupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier findById(Long id) throws NoSuchElementException {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Supplier with id " + id + " not found"));
    }

    @Override
    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier findByIndexAndFoodType(String type, int index) {
        return supplierRepository.findByIndexAndFoodType(type).get(index);
    }
}
