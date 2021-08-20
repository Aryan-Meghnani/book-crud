package com.example.bookcrud.exceptions;

import java.util.Date;

public class ErrorDetails {
    private Date timeStamp;
    private int errorCode;
    private String message;

    public ErrorDetails(Date timeStamp, int errorCode, String message) {
        this.timeStamp = timeStamp;
        this.errorCode = errorCode;
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timeStamp=" + timeStamp +
                ", errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
