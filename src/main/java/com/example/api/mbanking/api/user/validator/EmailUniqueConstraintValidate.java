package com.example.api.mbanking.api.user.validator;

import com.example.api.mbanking.api.auth.AuthMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EmailUniqueConstraintValidate implements ConstraintValidator<EmailUnique, String> {
    private final AuthMapper authMapper;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !authMapper.checkEmailIsExisted(email);
    }
}
