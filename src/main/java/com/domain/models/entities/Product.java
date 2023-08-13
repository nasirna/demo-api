package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_product")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id")

public class Product implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Column(name="product_name", length = 100)
    private String name;

    @NotEmpty(message = "Description is required")
    @Column(name="product_description", length = 500)
    private String Description;
    private double price;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "tbl_product_supplier",
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name="supplier_id"))    
    //@JsonManagedReference
    private Set<Supplier> suppliers;

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }
    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public Product() {
    }
    public Product(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        Description = description;
        this.price = price;
    }
    public Long getId() {
        return id;
    }
    public String getname() {
        return name;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setname(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return Description;
    }
    public double getPrice() {
        return price;
    }

    
}
