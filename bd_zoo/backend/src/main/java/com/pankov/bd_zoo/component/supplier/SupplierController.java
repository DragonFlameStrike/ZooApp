package com.pankov.bd_zoo.component.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/")
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{type}/{index}")
    public Supplier getSupplierByIndexAndFoodType(@PathVariable String type,@PathVariable int index) {
        return supplierService.findByIndexAndFoodType(type,index);
    }

    @GetMapping("/{id}")
    public Supplier getById(@PathVariable Long id) {
        return supplierService.findById(id);
    }

    @PostMapping("/")
    public Supplier create(@RequestBody Supplier supplier) {
        return supplierService.create(supplier);
    }
}
