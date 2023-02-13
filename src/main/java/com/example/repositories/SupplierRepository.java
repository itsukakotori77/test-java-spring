package com.example.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.entities.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long>{
 
    // Find By Email
    Supplier findByEmail(String email);

    // Find by Email (List)
    List<Supplier> findByEmailContainsOrderByIdDesc(String email);

    // Find by email (% by first)
    List<Supplier> findByEmailStartingWith(String prefix);

    // Find by email (Contains OR)
    List<Supplier> findByNameContainsOrEmailContains(String name, String email);
}
