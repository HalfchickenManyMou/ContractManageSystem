package com.example.study.exception.userException;

public class UserNotFoundException extends RuntimeException{
    private static final String message = "User does not exist";
    public UserNotFoundException(){
        super(message);
    }
}
