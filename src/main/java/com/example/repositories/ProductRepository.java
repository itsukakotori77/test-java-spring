package com.example.repositories;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    
    @Query("SELECT p from Product p where p.name LIKE :name")
    public List<Product> filter(@PathParam("name") String name);
}
