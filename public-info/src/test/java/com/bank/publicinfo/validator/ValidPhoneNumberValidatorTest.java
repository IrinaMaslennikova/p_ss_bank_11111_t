package com.bank.publicinfo.validator;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
class ValidPhoneNumberValidatorTest {

    @Mock
    ConstraintValidatorContext constraintValidatorContext;

    ValidPhoneNumberValidator validPhoneNumberValidator = new ValidPhoneNumberValidator();


    @Test
    public void should_NotBeValid_WhenNotNumber() {
        assertFalse(validPhoneNumberValidator.isValid(1L, constraintValidatorContext));
    }
    @Test
    public void should_NotBeValid_When7digits() {
        assertFalse(validPhoneNumberValidator.isValid(12345678901L, constraintValidatorContext));
    }

    @Test
    public void should_BeValid_When9digits() {
        assertTrue(validPhoneNumberValidator.isValid(89520025799L, constraintValidatorContext));
    }

}