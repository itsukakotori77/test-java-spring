package com.example.repositories;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.entities.Product;
import com.example.entities.Supplier;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    public List<Product> filter(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findBySupplier(@PathParam("supplier") Supplier supplier);
}
