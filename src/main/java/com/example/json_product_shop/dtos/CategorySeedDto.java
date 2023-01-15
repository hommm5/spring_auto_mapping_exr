package com.example.json_product_shop.dtos;


import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CategorySeedDto {

    @Expose
    @Length(min = 3, max = 15, message = "Wrong category length")
    private String name;

    public CategorySeedDto(String name) {
        this.name = name;
    }

    public CategorySeedDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
