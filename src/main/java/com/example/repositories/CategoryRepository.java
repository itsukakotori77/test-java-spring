package com.example.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Category;
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
    Page<Category> findByNameContains(String name, Pageable pageable);
}
