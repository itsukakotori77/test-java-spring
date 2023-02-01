package com.example.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.example.entities.Product;
import com.example.services.ProductService;

@RestController
@RequestMapping
public class ProductController 
{
    
    @Autowired
    private ProductService productService;

    @PostMapping("/v1/api/products")
    public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors)
    {

        // ==== Test code 1 ====
        // ResponseData<Product> responseData = new ResponseData<>();

        // if(errors.hasErrors()){
        //     for(ObjectError error : errors.getAllErrors()){
        //         responseData.getMessage().add(error.getDefaultMessage());
        //     }

        //     responseData.setStatus(false);
        //     responseData.setPayload(null);
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        // }

        // responseData.setStatus(true);
        // responseData.getMessage().add("Data Berhasil Diinput");
        // responseData.setPayload(productService.save(product));
        // return ResponseEntity.status(HttpStatus.OK).body(responseData);

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
            Product productCreate = productService.save(product);
            map.put("code", "00");
            map.put("message", "data berhasil diinput");
            map.put("data", productCreate);
            return new ResponseEntity<>(map, HttpStatus.OK);
            
        } catch (Exception e) {
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses input data");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
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
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
    @GetMapping("/v1/api/products/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") long id)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Product product = productService.findOne(id);

        if(product == null){
            map.put("code", "00");
            map.put("message", "data tidak ditemukan");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }

        map.put("code", "00");
        map.put("message", "data ditemukan");
        map.put("data", product);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/v1/api/products")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, Errors errors)
    {
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
            Product productCreate = productService.save(product);
            map.put("code", "00");
            map.put("message", "data berhasil diinput");
            map.put("data", productCreate);
            return new ResponseEntity<>(map, HttpStatus.OK);
            
        } catch (Exception e) {
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses input data");
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/v1/api/products/{id}")
    public ResponseEntity<?> deleteData(@PathVariable("id") Long id)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>(); 
        productService.removeOne(id);
        map.put("code", "00");
        map.put("message", "data berhasil dihapus");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
