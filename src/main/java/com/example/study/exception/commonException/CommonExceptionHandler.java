package com.example.study.exception.commonException;

import com.example.study.exception.userException.UserNotFoundException;
import com.example.study.model.network.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Header> DataNotFoundException(DataNotFoundException ex) {
        return new ResponseEntity<>(Header.ERROR(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
