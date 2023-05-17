package com.example.api.mbanking.api.user.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailUniqueConstraintValidate.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EmailUnique {
    String message() default "Email is already exited.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
