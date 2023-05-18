package com.example.api.mbanking.api.user.validator.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordCheckIsStrong.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface PasswordIsStrong {
    String message() default "Password is weak.";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
