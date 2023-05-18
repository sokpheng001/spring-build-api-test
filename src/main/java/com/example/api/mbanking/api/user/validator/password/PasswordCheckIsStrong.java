package com.example.api.mbanking.api.user.validator.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.passay.*;

@RequiredArgsConstructor
public class PasswordCheckIsStrong implements ConstraintValidator<PasswordIsStrong, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(
                new AllowedCharacterRule(new char[] { 'a', 'b', 'c' }),
                new CharacterRule(EnglishCharacterData.LowerCase, 5),
                new LengthRule(8, 10)
        );
        PasswordData passwordData = new PasswordData(password);
        RuleResult validate = passwordValidator.validate(passwordData);
        System.out.println(validate.getDetails().get(0));
        if(validate.isValid()) return true;
        context.buildConstraintViolationWithTemplate(passwordValidator.getMessages(validate).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
