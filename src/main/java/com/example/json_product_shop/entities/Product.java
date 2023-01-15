package com.example.json_product_shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Length(min = 3, message = "Wrong product length")
    private String name;

    @DecimalMin(value = "0")
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> categories;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Seller can not be missing")
    private User seller;

    public Product() {
    }

    public Product(String name, BigDecimal price, long buyerId, long sellerId) {
        this.name = name;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
