package com.salon.manager.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}