package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Product;
import com.example.services.ProductService;

@RestController
@RequestMapping
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping("/v1/api/products")
    public Product create(@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/v1/api/products")
    public Iterable<Product> findAll(){
        return productService.findAll();
    }
    
    @GetMapping("/v1/api/products/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @PutMapping("/v1/api/products")
    public Product update(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/v1/api/products/{id}")
    public void removeOne(@PathVariable("id") Long id){
        productService.removeOne(id);
    }
}
