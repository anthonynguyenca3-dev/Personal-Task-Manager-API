package com.anthonynguyen.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException error) {

            String errors;

        if (error.getFieldError() != null){
            errors = error.getFieldError().getDefaultMessage();
        } else {
            errors = "Invalid request.";
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

}
