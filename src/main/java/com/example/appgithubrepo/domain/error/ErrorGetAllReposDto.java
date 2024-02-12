package com.example.appgithubrepo.domain.error;

public record ErrorGetAllReposDto(int httpStatus, String message) {
}