package com.example.appgithubrepo.domain.error;

import org.springframework.http.HttpStatus;

public record ErrorBadHeaderResponseDto(HttpStatus httpStatus, String message) {
}