package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long>{
    
}
