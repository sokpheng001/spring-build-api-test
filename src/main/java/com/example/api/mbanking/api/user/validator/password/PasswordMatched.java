package com.example.api.mbanking.api.user.validator.password;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = PasswordMatchedClass.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatched {
    String message() default "Password is not matched";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String password();
    String confirmPassword();
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        PasswordMatched[] value();
    }
}
