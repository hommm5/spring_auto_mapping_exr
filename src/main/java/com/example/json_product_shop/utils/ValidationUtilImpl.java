package com.example.json_product_shop.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil{

    private Validator validator;

    public ValidationUtilImpl(){
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }


    @Override
    public <T> boolean isValid(T entity) {
        return validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> getViolations(T entity) {
        return validator.validate(entity);
    }
}
