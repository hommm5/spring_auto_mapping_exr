package com.example.json_product_shop.services;

import com.example.json_product_shop.dtos.CategorySeedDto;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDto);
}
