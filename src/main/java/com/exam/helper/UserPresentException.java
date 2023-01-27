package com.exam.helper;

public class UserPresentException extends Exception{
    UserPresentException(){
        super("User Al Ready Present Exception");
    }

    UserPresentException(String exceptionMessage){
        super(exceptionMessage);
    }

}
