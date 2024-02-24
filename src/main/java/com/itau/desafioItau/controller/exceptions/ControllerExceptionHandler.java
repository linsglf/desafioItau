package com.itau.desafioItau.controller.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> NullObject(NullPointerException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Bad Request", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> Validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        StringBuilder errorField = new StringBuilder("The following fields are required or invalid: ");
        for (FieldError f : fieldErrors) {
            errorField.append(f.getField()).append(" - ").append(f.getDefaultMessage()).append(", ");
        }
        errorField.delete(errorField.length() - 2, errorField.length());
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Validation Error", errorField.toString(), request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolation(DataIntegrityViolationException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Bad Request", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<StandardError> EntityExists(EntityExistsException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Conflict", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(409).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> ObjectNotFound(Exception exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Not Found", exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(404).body(error);
    }
}
