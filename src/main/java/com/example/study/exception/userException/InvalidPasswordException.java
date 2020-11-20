package com.example.study.exception.userException;

public class InvalidPasswordException extends RuntimeException{
    private static final String message = "Invalid Password";
    public InvalidPasswordException(){
        super(message);
    }
}
