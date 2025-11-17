package com.study.feedback_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullFieldValidatorImpl implements ConstraintValidator<NullFieldValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return !value.trim().equalsIgnoreCase("null");
    }
}