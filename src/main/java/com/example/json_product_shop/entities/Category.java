package com.example.json_product_shop.entities;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    @Length(min = 3, max = 15, message = "Wrong category length")
    private String name;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private List<Product> products;

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
