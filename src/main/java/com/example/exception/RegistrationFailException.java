package com.example.exception;

public class RegistrationFailException extends RuntimeException {
    public RegistrationFailException(String message) {
        super(message);
    }
}