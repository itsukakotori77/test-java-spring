package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.entities.Supplier;
import com.example.repositories.SupplierRepository;

@Service
@Transactional
public class SupplierService {

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


}
