package com.exam.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

/** CustomizedExceptionHandling.java class:
This class is annotated with @ControllerAdvice which means that
this class will be able to handle the exceptions occured from any
of the Controllers(not only limited to the ResourceController.java).
It extends the class ResponseEntityExceptionHandler which exposes the
 functionality required to implement this feature. The @ExceptionHandler
 annotation is required to handle the type of exceptions which are thrown
 and the corresponding method will be able to handle the exceptions.*/
@ControllerAdvice
public class CustomizedExceptionHandle extends ResponseEntityExceptionHandler {

//    username not found
    @ExceptionHandler(UserNameNotFoundException.class)
    public ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException exception, WebRequest request){

//        this is our response, on that class we have error message
        ExceptionResponse exceptionResponse =  new ExceptionResponse();
        exceptionResponse.setLocalTime(LocalDateTime.now());
        exceptionResponse.setMessage("Username not Found exception");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

//    no userid present
    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<Object> handlerUserIdNotFoundException(UserIdNotFoundException exception, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("Not user present with current user Id");
        exceptionResponse.setLocalTime(LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }
}
