package com.example.json_product_shop.services;

import com.example.json_product_shop.dtos.UserSeedDto;
import com.example.json_product_shop.entities.User;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDto);
    User findUserById(long id);

    long countAllUsers();
}
