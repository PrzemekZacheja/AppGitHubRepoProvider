package com.example.appgithubrepo.domain.error;

import org.springframework.http.HttpStatus;

public record ErrorHeaderResponseDto(HttpStatus httpStatus, String message) {
}