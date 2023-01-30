package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
