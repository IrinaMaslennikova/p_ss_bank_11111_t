package com.bank.publicinfo.handler;

import com.bank.publicinfo.exception.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionHandler для {@link ValidatorException}
 */

@ControllerAdvice
public class ValidatorExceptionHandler {
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<String> validatorException (ValidatorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
