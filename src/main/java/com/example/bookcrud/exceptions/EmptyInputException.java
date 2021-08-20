package com.example.bookcrud.exceptions;

import java.util.Date;

public class EmptyInputException extends RuntimeException{
    private int errorCode;
    private String message;

    public EmptyInputException(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public EmptyInputException() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
