package com.bank.publicinfo.handler;

import com.bank.publicinfo.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionHandler для {@link DataNotFoundException}
 */

@ControllerAdvice
public class DataNotFoundExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> dataNotFoundException (DataNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

