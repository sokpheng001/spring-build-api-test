package com.example.api.mbanking.api.user.validator.password;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchedClass implements ConstraintValidator<PasswordMatched, Object> {
    private String password;
    private String confirmPassword;
    private String message;

    @Override
    public void initialize(PasswordMatched constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        boolean isValid = false;
        if(passwordValue!=null){
            isValid = passwordValue.equals(confirmPasswordValue);
        }
        if(!isValid){
            //
            context.disableDefaultConstraintViolation();
            //
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(password)
                    .addConstraintViolation();
            //
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmPassword)
                    .addConstraintViolation();
        }
        return isValid;
    }
}
