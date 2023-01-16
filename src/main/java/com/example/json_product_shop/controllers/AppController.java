package com.example.json_product_shop.controllers;

import com.example.json_product_shop.dtos.CategorySeedDto;
import com.example.json_product_shop.dtos.ProductAndSellerDto;
import com.example.json_product_shop.dtos.ProductSeedDto;
import com.example.json_product_shop.dtos.UserSeedDto;
import com.example.json_product_shop.globalConstant.GlobalConstant;
import com.example.json_product_shop.services.CategoryService;
import com.example.json_product_shop.services.ProductService;
import com.example.json_product_shop.services.UserService;
import com.example.json_product_shop.utils.FileIOUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileIOUtil fileIOUtil;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileIOUtil fileIOUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
    }

    @Override
    public void run(String... args) throws Exception {

        seedUsers();
        seedCategories();
        seedProducts();
        exportProducts();
    }

    private void exportProducts() throws IOException {
        List<ProductAndSellerDto> dtos = productService.getAllProductsInRange();
        String json = gson.toJson(dtos);
        fileIOUtil.write(json, GlobalConstant.FILE_PATH_OUTPUTEX1);
    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = gson
                .fromJson(new FileReader(GlobalConstant.FILE_PATH_PRODUCTS), ProductSeedDto[].class);
        productService.seedProducts(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedDto[] dtos = gson
                .fromJson(new FileReader(GlobalConstant.FILE_PATH_USERS), UserSeedDto[].class);
        userService.seedUsers(dtos);
    }

    private void seedCategories() throws FileNotFoundException {
        CategorySeedDto[] dtos = gson
                .fromJson(new FileReader(GlobalConstant.FILE_PATH_CATEGORIES), CategorySeedDto[].class);
        categoryService.seedCategories(dtos);
    }
}
