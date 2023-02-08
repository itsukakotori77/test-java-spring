package com.example.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryData {

    private Long id;
    
    @NotEmpty(message = "Nama harus diisi")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
