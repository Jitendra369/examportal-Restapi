package com.exam.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

// This class attributes will be returned to the client as a part of customized exception handling.
public class ExceptionResponse {
    private String message;
    private LocalDateTime localTime;
    private HttpStatus status;

    public ExceptionResponse(String message, LocalDateTime localTime, HttpStatus status) {
        this.message = message;
        this.localTime = localTime;
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public ExceptionResponse(){}

    public ExceptionResponse(String message, LocalDateTime localTime) {
        this.message = message;
        this.localTime = localTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }
}
