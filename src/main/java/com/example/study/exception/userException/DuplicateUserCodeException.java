package com.example.study.exception.userException;

public class DuplicateUserCodeException extends RuntimeException{
    private static final String message = "This user_code is a duplicate";
    public DuplicateUserCodeException(){
        super(message);
    }
}
