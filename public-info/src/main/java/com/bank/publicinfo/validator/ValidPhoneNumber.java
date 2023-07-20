package com.bank.publicinfo.validator;

import com.bank.publicinfo.entity.Branch;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Валидатор ValidPhoneNumber для поля phone {@link Branch}
 */

@Documented
@Constraint(validatedBy = ValidPhoneNumberValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Номер телефона должен начинаться на '8'";
    Class<?> [] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

