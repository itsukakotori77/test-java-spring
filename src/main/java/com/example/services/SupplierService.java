package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Supplier;
import com.example.repositories.SupplierRepository;

@Service
@Transactional
public class SupplierService 
{

    @Autowired
    private SupplierRepository supplierRepo;

    public Supplier save(Supplier supplier)
    {
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id)
    {
        Optional<Supplier> supplier = supplierRepo.findById(id);

        if(!supplier.isPresent()){
            return null;
        }

        return supplier.get();
    }

    public Iterable<Supplier> findAll()
    {
        return supplierRepo.findAll();
    }

    public void removeOne(Long id)
    {
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail(String email)
    {
        Supplier supplier = supplierRepo.findByEmail(email);
        
        if(supplier == null){
            return null;
        }

        return supplier;
    }

    public List<Supplier> findByEmailList(String email)
    {
        List<Supplier> supplier = supplierRepo.findByEmailContainsOrderByIdDesc(email);

        if(supplier == null){
            return new ArrayList<>();
        }

        return supplier;

    }

    public List<Supplier> findByEmailStart(String prefix)
    {
        List<Supplier> supplier = supplierRepo.findByEmailStartingWith(prefix);

        if(supplier == null){
            return new ArrayList<>();
        }

        return supplier;
    }

    public List<Supplier> filter(String name, String email)
    {
        List<Supplier> supplier = supplierRepo.findByNameContainsOrEmailContains(name, email);

        if(supplier == null){
            return new ArrayList<>();
        }

        return supplier;
    } 

}
