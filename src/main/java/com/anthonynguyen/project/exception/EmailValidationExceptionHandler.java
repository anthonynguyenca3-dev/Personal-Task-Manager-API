package com.anthonynguyen.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailValidationExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExist(EmailAlreadyExistsException alreadyExist) {

        String alreadyExistMessage = alreadyExist.getMessage();

        return new ResponseEntity<>(alreadyExistMessage, HttpStatus.CONFLICT);

    }

}
