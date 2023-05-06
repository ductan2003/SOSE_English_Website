package com.elearningweb.library.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordConfirmationValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordConfirmation {
    String password();
    String confirmPassword();
    String message() default "Passwords must match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        PasswordConfirmation[] value();
    }
}
