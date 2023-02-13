package com.example.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;
import com.example.dto.FilterData;
import com.example.dto.ProductData;
import com.example.dto.SupplierData;
import com.example.entities.Product;
import com.example.entities.Supplier;
import com.example.services.ProductService;

@RestController
@RequestMapping
public class ProductController 
{
    
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/v1/api/products")
    public ResponseEntity<?> create(@Valid @RequestBody ProductData productData, Errors errors)
    {
        // ==== Test code 2 ====
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Map<String, String> message =  new HashMap<>();
        
        if(errors.hasErrors()){
            errors.getAllErrors().forEach((error) -> {
                message.put(((FieldError) error).getField(), error.getDefaultMessage());
            });

            map.put("code", "01");
            map.put("message", "validasi error");
            map.put("data", message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

        try {
            Product product = modelMapper.map(productData, Product.class);
            map.put("code", "00");
            map.put("message", "data berhasil diinput");
            map.put("data", productService.save(product));
            return ResponseEntity.status(HttpStatus.OK).body(map);
            
        } catch (Exception e) {
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses input data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @GetMapping("/v1/api/products")
    public ResponseEntity<?> all()
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Iterable<Product> productList = productService.findAll();
        map.put("code", "00");
        map.put("message", "data berhasil ditampilkan");
        map.put("data", productList);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    
    @GetMapping("/v1/api/products/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") long id)
    {
        HashMap<String, Object> map = new HashMap<>();
        Product product = productService.findOne(id);

        if(product == null){
            map.put("code", "00");
            map.put("message", "data tidak ditemukan");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }

        map.put("code", "00");
        map.put("message", "data ditemukan");
        map.put("data", product);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping("/v1/api/products")
    public ResponseEntity<?> update(@Valid @RequestBody ProductData productData, Errors errors)
    {
        // ==== Test code 2 ====
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Map<String, String> message =  new HashMap<>();
        
        if(errors.hasErrors()){
            errors.getAllErrors().forEach((error) -> {
                message.put(((FieldError) error).getField(), error.getDefaultMessage());
            });

            map.put("code", "01");
            map.put("message", "validasi error");
            map.put("data", message);

            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }

        try {
            Product product = modelMapper.map(productData, Product.class);
            map.put("code", "00");
            map.put("message", "data berhasil diinput");
            map.put("data", productService.save(product));
            // return new ResponseEntity<>(map, HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(map);
            
        } catch (Exception e) {
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses input data");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @DeleteMapping("/v1/api/products/{id}")
    public ResponseEntity<?> deleteData(@PathVariable("id") Long id)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>(); 
        productService.removeOne(id);
        map.put("code", "00");
        map.put("message", "data berhasil dihapus");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PostMapping("/v1/api/products/{product_id}")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierData supplierData, @PathVariable("product_id") Long productId)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Supplier supplier = modelMapper.map(supplierData, Supplier.class);
        productService.addSupplier(supplier, productId);
        try {
            map.put("code", "00");
            map.put("message", "Supplier berhasil ditambahkan");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch(Exception e){
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses penambahan supplier");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/v1/api/product-filter")
    public ResponseEntity<?> filter(@RequestBody FilterData filter)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Product> data = productService.filter(filter.getName());

        if(data.isEmpty()){

            map.put("code", "00");
            map.put("message", "Data tidak ditemukan");
            map.put("data", null);
            
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }

        map.put("code", "00");
        map.put("message", "Data berhasil difilter");
        map.put("data", data);
        
        return ResponseEntity.status(HttpStatus.OK).body(map);

    }

    @PostMapping("/v1/api/product-filter-supplier/{supplierId}")
    public ResponseEntity<?> findBySupplier(@PathVariable("supplierId") Long supplierId)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<Product> data = productService.findBySupplier(supplierId);

        if(data.isEmpty()){

            map.put("code", "00");
            map.put("message", "Data tidak ditemukan");
            map.put("data", data);
            
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }

        map.put("code", "00");
        map.put("message", "Data berhasil difilter");
        map.put("data", data);
        
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    
}
