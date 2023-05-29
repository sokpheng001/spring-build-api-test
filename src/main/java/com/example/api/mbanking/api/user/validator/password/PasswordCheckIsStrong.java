package com.example.api.mbanking.api.user.validator.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Array;
import org.passay.*;

import java.util.ArrayList;
import java.util.Arrays;


@RequiredArgsConstructor
public class PasswordCheckIsStrong implements ConstraintValidator<PasswordIsStrong, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(
                Arrays.asList(
                        new LengthRule(5,10),
                        new CharacterRule(EnglishCharacterData.LowerCase,1),
                        new CharacterRule(EnglishCharacterData.UpperCase,1),
                        new CharacterRule(EnglishCharacterData.Special,1),
                        new WhitespaceRule()
                )
        );
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);
        if(ruleResult.isValid()){
            return true;
        }
        context.buildConstraintViolationWithTemplate(passwordValidator.getMessages(ruleResult).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
