package com.anthonynguyen.project.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(1)
@RestControllerAdvice
public class EmailValidationExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExist(EmailAlreadyExistsException alreadyExist) {

        String alreadyExistMessage = alreadyExist.getMessage();

        return new ResponseEntity<>(alreadyExistMessage, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(AuthenticatedUserNotFoundException.class)
    public ResponseEntity<String> handleAuthenticatedUserNotFound(AuthenticatedUserNotFoundException notFound) {

        String notFoundMessage = notFound.getMessage();

        return new ResponseEntity<>(notFoundMessage, HttpStatus.UNAUTHORIZED);

    }

}
