package com.example.json_product_shop.services.impl;

import com.example.json_product_shop.dtos.CategorySeedDto;
import com.example.json_product_shop.entities.Category;
import com.example.json_product_shop.repositories.CategoryRepository;
import com.example.json_product_shop.services.CategoryService;
import com.example.json_product_shop.utils.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDto) {
        Arrays.stream(categorySeedDto)
                .forEach(c->{
                    if (categoryRepository.findCategoryByName(c.getName()) == null){
                        if (validationUtil.isValid(c)){
                            Category category = modelMapper.map(c, Category.class);
                            categoryRepository.saveAndFlush(category);
                        }else {
                            validationUtil.getViolations(c)
                                    .stream().map(ConstraintViolation::getMessage)
                                    .forEach(System.out::println);
                        }
                    }

                });

    }

    @Override
    public long countAllCategories() {
        return categoryRepository.count();
    }

    @Override
    public Category findCategoryById(long id) {
        return categoryRepository.findById(id);
    }
}
