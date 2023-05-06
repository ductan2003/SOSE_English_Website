package com.elearningweb.library.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object> {
    private String password;
    private String confirmPassword;

    @Override
    public void initialize(PasswordConfirmation constraintAnnotation) {
        this.password = constraintAnnotation.password();
        this.confirmPassword = constraintAnnotation.confirmPassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(o).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(o).getPropertyValue(confirmPassword);
        if(passwordValue != null) return passwordValue.equals(confirmPasswordValue);
        return confirmPasswordValue == null;
    }
}
