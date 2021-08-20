package com.example.bookcrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class Advice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<?> handleEmptyInput(EmptyInputException emptyInputException){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), emptyInputException.getErrorCode(), emptyInputException.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> noElementException(NotFoundException notFoundException){
        ErrorDetails errorDetails = new ErrorDetails(new Date(),notFoundException.getErrorCode(),notFoundException.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
