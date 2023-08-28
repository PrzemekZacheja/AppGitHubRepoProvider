package com.example.appgithubrepo.domain.error;

public class BadHeaderErrorException extends RuntimeException {
    public BadHeaderErrorException(String message) {
        super(message);
    }
}