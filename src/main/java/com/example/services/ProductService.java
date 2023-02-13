package com.example.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Product;
import com.example.entities.Supplier;
import com.example.repositories.ProductRepository;

@Service
@Transactional
public class ProductService 
{
    
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private SupplierService supplierService;

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

    public List<Product> filter(String name)
    {
        return productRepo.filter("%"+name+"%");
    }

    public List<Product> findBySupplier(Long supplierId)
    {
        Supplier supplier = supplierService.findOne(supplierId);

        if(supplier == null){
            return new ArrayList<>();
        }

        return productRepo.findBySupplier(supplier);
    }
}
