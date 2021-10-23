package com.pipertzis.FarmHelper_BackEnd.Exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Such Element Found");
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundHandler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to find with this id");
    }

    @ExceptionHandler(value = ConnectException.class)
    public ResponseEntity<?> connectionFailureHandler() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("There was some problem connecting with the database." +
                "\nPlease be patient." +
                "\nThank you!");
    }

    @ExceptionHandler(value = PSQLException.class)
    public ResponseEntity<?> duplicateKeyHandler(PSQLException psqlException) {
        return ResponseEntity.status(HttpStatus.FOUND).body(psqlException.getServerErrorMessage().getDetail());
    }


}
