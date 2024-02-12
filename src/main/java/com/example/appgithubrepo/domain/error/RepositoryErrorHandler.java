package com.example.appgithubrepo.domain.error;

import com.example.appgithubrepo.infrastructure.controller.Controller;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = Controller.class)
public class RepositoryErrorHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorGetAllReposDto handleUserNameNotFoundException(FeignException.NotFound exception) {
        return new ErrorGetAllReposDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(HeaderErrorException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorHeaderResponseDto handleBadHeaderErrorException(HeaderErrorException exception) {
        return new ErrorHeaderResponseDto(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
    }

}