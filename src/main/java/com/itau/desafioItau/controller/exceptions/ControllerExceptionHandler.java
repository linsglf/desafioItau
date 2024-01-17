package com.itau.desafioItau.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> NullObject(NullPointerException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Bad Request", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }
}
