package com.example.study.exception.userException;

public class DuplicateEmailFoundException extends RuntimeException {
    private static final String message = "This email is a duplicate";
    public DuplicateEmailFoundException(){
        super(message);
    }
}
