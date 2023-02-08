package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Category;
import com.example.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepo;

    public Category save(Category category)
    {
        return categoryRepo.save(category);
    }

    public Category findOne(Long id)
    {
        Optional<Category> category = categoryRepo.findById(id);

        if(!category.isPresent()){
            return null;
        }

        return category.get();
    }

    public Iterable<Category> findAll()
    {
        return categoryRepo.findAll();
    }

    public void removeOne(Long id)
    {
        categoryRepo.deleteById(id);
    }
}
