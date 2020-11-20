package com.example.study.exception.commonException;

public class DataNotFoundException extends RuntimeException{
    private static final String message = "Data does not exist";
    public DataNotFoundException(){
        super(message);
    }
}
