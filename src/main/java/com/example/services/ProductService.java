package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Product;
import com.example.entities.Supplier;
import com.example.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo;

    public Product save(Product product)
    {
        return productRepo.save(product);
    }

    public Product findOne(Long id)
    {
        Optional<Product> product = productRepo.findById(id);

        if(!product.isPresent()){
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll()
    {
        return productRepo.findAll();
    }

    public void removeOne(long id)
    {
        productRepo.deleteById(id);
    }

    public void addSupplier(Supplier supplier, Long productId)
    {
        Product product = findOne(productId);

        if(product == null){
            throw new RuntimeException("Not Found");
        }

        product.getSuppliers().add(supplier);
        save(product);
    }
}
