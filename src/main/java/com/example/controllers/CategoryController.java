package com.example.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.CategoryData;
import com.example.entities.Category;
import com.example.services.CategoryService;

@RestController
@RequestMapping("/v1/api/category")
public class CategoryController 
{
 
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryData categoryData, Errors errors)
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
            Category supplier = modelMapper.map(categoryData, Category.class);
            map.put("code", "00");
            map.put("message", "data berhasil diinput");
            map.put("data", categoryService.save(supplier));

            return ResponseEntity.status(HttpStatus.OK).body(map);
            
        } catch (Exception e) {
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses input data");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> all()
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Iterable<Category> list = categoryService.findAll();
        map.put("code", "00");
        map.put("message", "data berhasil ditampilkan");
        map.put("data", list);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") long id)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        Category category = categoryService.findOne(id);

        if(category == null){
            map.put("code", "00");
            map.put("message", "data tidak ditemukan");
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }

        map.put("code", "00");
        map.put("message", "data ditemukan");
        map.put("data", category);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody CategoryData categoryData, Errors errors)
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
            Category category = modelMapper.map(categoryData, Category.class);
            map.put("code", "00");
            map.put("message", "data berhasil diinput");
            map.put("data", categoryService.save(category));

            return ResponseEntity.status(HttpStatus.OK).body(map);
            
        } catch (Exception e) {
            map.put("code", "01");
            map.put("message", "terjadi kesalahan pada proses input data");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteData(@PathVariable("id") Long id)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>(); 
        categoryService.removeOne(id);
        map.put("code", "00");
        map.put("message", "data berhasil dihapus");
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PostMapping("/filter/{size}/{page}")
    public ResponseEntity<?> filter(@RequestBody CategoryData categoryData, @PathVariable("size") int size, @PathVariable("page") int page)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        try {
            Pageable pageable = PageRequest.of(page, size);
            Iterable<Category> list = categoryService.findByName(categoryData.getName(), pageable);
            map.put("code", "00");
            map.put("message", "data berhasil ditampilkan");
            map.put("data", list);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch(Exception e) {
            map.put("code", "01");
            map.put("message", "internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

    @PostMapping("/filter/{size}/{page}/{sort}")
    public ResponseEntity<?> filter(@RequestBody CategoryData categoryData, @PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("sort") String sort)
    {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            if(sort.equalsIgnoreCase("desc")){
                pageable = PageRequest.of(page, size, Sort.by("id").descending());
            }
            
            Iterable<Category> list = categoryService.findByName(categoryData.getName(), pageable);
            map.put("code", "00");
            map.put("message", "data berhasil ditampilkan");
            map.put("data", list);
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch(Exception e) {
            map.put("code", "01");
            map.put("message", "internal server error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }

}
