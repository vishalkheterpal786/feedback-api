package com.study.feedback_api.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NullFieldValidatorImpl.class)
public @interface NullFieldValidator {
    String message() default "Field cannot be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}