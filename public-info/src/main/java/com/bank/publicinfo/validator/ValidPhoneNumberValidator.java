package com.bank.publicinfo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Реализация валидатора {@link ValidPhoneNumber}
 */

public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, Long> {

    @Override
    public boolean isValid(Long phone, ConstraintValidatorContext constraintValidatorContext) {
        return phone.toString().charAt(0) == '8' && phone.toString().length() == 11;
    }
}

