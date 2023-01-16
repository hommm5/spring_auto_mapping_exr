package com.example.json_product_shop.repositories;

import com.example.json_product_shop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName (String name);
    List<Product> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc (BigDecimal lower, BigDecimal upper);
}
