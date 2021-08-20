package com.example.bookcrud.exceptions;

public class NotFoundException extends RuntimeException {
    private int errorCode;
    private String message;

    public NotFoundException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public NotFoundException() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
