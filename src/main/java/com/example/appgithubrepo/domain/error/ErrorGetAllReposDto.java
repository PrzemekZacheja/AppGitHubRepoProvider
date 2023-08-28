package com.example.appgithubrepo.domain.error;

import org.springframework.http.HttpStatus;

public record ErrorGetAllReposDto(HttpStatus httpStatus, String message) {
}