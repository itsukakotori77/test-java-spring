package com.example.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductData {

    private Long id;
    
    @NotEmpty(message = "Nama harus diisi")
    private String name;

    @DecimalMax("100000.0") @DecimalMin("0.0") 
    @NotNull(message = "Harga harus diisi")
    private Double harga;
    
    @NotEmpty(message = "Deskripsi harus diisi")
    private String deskripsi;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
