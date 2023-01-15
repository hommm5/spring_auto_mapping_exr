package com.example.json_product_shop.controllers;

import com.example.json_product_shop.dtos.CategorySeedDto;
import com.example.json_product_shop.dtos.UserSeedDto;
import com.example.json_product_shop.globalConstant.GlobalConstant;
import com.example.json_product_shop.services.CategoryService;
import com.example.json_product_shop.services.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Component
public class AppController implements CommandLineRunner {

    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        
        seedCategories();
        seedUsers();
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
