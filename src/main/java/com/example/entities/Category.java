package com.example.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "category")
public class Category implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nama", length = 50, unique = true)
    private String name;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;


    // Constructor
    public Category() {}

    public Category(long id, @NotEmpty String name, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getCreated_at() {
        return created_at;
    }


    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }


    public Date getUpdated_at() {
        return updated_at;
    }


    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
}
