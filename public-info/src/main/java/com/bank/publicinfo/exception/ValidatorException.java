package com.bank.publicinfo.exception;

import com.bank.publicinfo.entity.Branch;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение ValidatorException для {@link Branch}
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidatorException extends RuntimeException {
    private static final long serialVersionUID = -3949585886968568733L;
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable e) {
        super(message, e);
    }
}

