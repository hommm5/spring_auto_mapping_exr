package com.example.json_product_shop.repositories;
import com.example.json_product_shop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName (String name);
}
