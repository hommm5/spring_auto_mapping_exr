package com.example.json_product_shop.services;

import com.example.json_product_shop.dtos.ProductAndSellerDto;
import com.example.json_product_shop.dtos.ProductSeedDto;
import com.example.json_product_shop.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);
    List<ProductAndSellerDto> getAllProductsInRange();
}
