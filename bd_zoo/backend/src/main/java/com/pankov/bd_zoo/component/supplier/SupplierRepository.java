package com.pankov.bd_zoo.component.supplier;

import com.pankov.bd_zoo.component.animal.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT supplier FROM Supplier supplier WHERE supplier.type = :type")
    List<Supplier> findByIndexAndFoodType(@Param("type") String type);
}
