package com.bank.publicinfo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение NotFoundException для entity
 */

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3949585886968568733L;
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable e) {
        super(message, e);
    }
}
