package com.example.json_product_shop.services.impl;

import com.example.json_product_shop.dtos.UserSeedDto;
import com.example.json_product_shop.entities.User;
import com.example.json_product_shop.repositories.UserRepository;
import com.example.json_product_shop.services.UserService;
import com.example.json_product_shop.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedUsers(UserSeedDto[] userSeedDto) {
        Arrays.stream(userSeedDto)
                .forEach(u -> {
                    if (userRepository.findUserByLastName(u.getLastName()) == null){
                        if (validationUtil.isValid(u)){
                            User user = modelMapper.map(u, User.class);
                            userRepository.saveAndFlush(user);

                        }else {
                            validationUtil.getViolations(u)
                                    .stream().map(ConstraintViolation::getMessage)
                                    .forEach(System.out::println);
                        }
                    }
                });
    }
}
