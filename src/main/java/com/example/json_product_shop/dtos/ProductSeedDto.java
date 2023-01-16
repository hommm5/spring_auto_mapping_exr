package com.example.json_product_shop.dtos;
import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.DecimalMin;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class ProductSeedDto {

    @Expose
    @Length(min = 3, message = "Wrong product length")
    private String name;

    @Expose
    @DecimalMin(value = "0")
    private BigDecimal price;

    public ProductSeedDto() {
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
}
