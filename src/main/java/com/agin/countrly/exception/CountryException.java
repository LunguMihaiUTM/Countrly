package com.agin.countrly.exception;

import java.io.Serial;

public class CountryException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public CountryException(String message) {
        super(message);
    }
}
