package com.example.json_product_shop.services;

import com.example.json_product_shop.dtos.CategorySeedDto;
import com.example.json_product_shop.entities.Category;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDto);
    long countAllCategories();
    Category findCategoryById (long id);
}
