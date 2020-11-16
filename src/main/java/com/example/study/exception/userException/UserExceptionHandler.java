package com.example.study.exception.userException;

import com.example.study.model.network.Header;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Header> UserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(Header.ERROR(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Header> InvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseEntity<>(Header.ERROR(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailFoundException.class)
    public ResponseEntity<Header> DuplicateEmailFoundException(DuplicateEmailFoundException ex) {
        return new ResponseEntity<>(Header.ERROR(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateUserCodeException.class)
    public ResponseEntity<Header> DuplicateUserCodeException(DuplicateUserCodeException ex) {
        return new ResponseEntity<>(Header.ERROR(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
