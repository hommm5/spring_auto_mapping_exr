package com.example.json_product_shop.services.impl;

import com.example.json_product_shop.dtos.ProductAndSellerDto;
import com.example.json_product_shop.dtos.ProductSeedDto;
import com.example.json_product_shop.entities.Category;
import com.example.json_product_shop.entities.Product;
import com.example.json_product_shop.entities.User;
import com.example.json_product_shop.repositories.ProductRepository;
import com.example.json_product_shop.services.CategoryService;
import com.example.json_product_shop.services.ProductService;
import com.example.json_product_shop.services.UserService;
import com.example.json_product_shop.utils.ValidationUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final ValidationUtil validationUtil;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, ValidationUtil validationUtil, CategoryService categoryService, UserService userService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.categoryService = categoryService;
        this.userService = userService;
    }


    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        Arrays.stream(productSeedDtos).forEach(p -> {
            if (productRepository.findProductByName(p.getName()) == null) {
                if (validationUtil.isValid(p)) {

                    Product product = modelMapper.map(p, Product.class);

                    product.setCategories(randomCategories());

                    product.setSeller(selectSeller());

                    product.setBuyer(selectBuyer());

                    productRepository.saveAndFlush(product);

                } else {
                    validationUtil.getViolations(p).stream()
                            .map(ConstraintViolation::getMessage)
                            .forEach(System.out::println);
                }
            }
        });
    }

    @Override
    public List<ProductAndSellerDto> getAllProductsInRange() {
        return productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc
                        (BigDecimal.valueOf(500), BigDecimal.valueOf(1000))
                .stream().map(p -> {
                    ProductAndSellerDto productAndSellerDto = modelMapper.map(p, ProductAndSellerDto.class);
                    productAndSellerDto.setSeller(String.format("%s %s", p.getSeller().getFirstName(), p.getSeller().getLastName()));
                    System.out.println();
                    return productAndSellerDto;
                })
                .collect(Collectors.toList());
    }


    private User selectBuyer() {
        User user = null;

        Random randomNumber = new Random();
        long numberOfUsers = userService.countAllUsers();

        long id = randomNumber.nextLong(numberOfUsers) + 1;
        if (randomNumber.nextInt(4) % 2 == 0) {
            user = userService.findUserById(id);
        }

        return user;
    }

    private User selectSeller() {

        User user = null;

        Random randomNumber = new Random();
        long numberOfUsers = userService.countAllUsers();
        long id = randomNumber.nextLong(numberOfUsers) + 1;
        user = userService.findUserById(id);

        return user;
    }

    private List<Category> randomCategories() {
        List<Category> categories = new ArrayList<>();

        Random randomNumber = new Random();

        int countAllCategories = (int) categoryService.countAllCategories();
        int numberOfCategories = randomNumber.nextInt(countAllCategories);

        for (int i = 0; i < numberOfCategories; i++) {
            Category category = categoryService
                    .findCategoryById(randomNumber.nextInt(countAllCategories) + 1);

            categories.add(category);
        }

        return categories;
    }
}
