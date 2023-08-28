package com.example.appgithubrepo.domain.error;

public class HeaderErrorException extends RuntimeException {
    public HeaderErrorException(String message) {
        super(message);
    }
}