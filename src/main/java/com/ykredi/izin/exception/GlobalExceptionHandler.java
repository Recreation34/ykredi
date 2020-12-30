package com.ykredi.izin.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ResourceBundle;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<Object> handleContentNotFoundException(ContentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InputNotValidException.class)
    public ResponseEntity<Object> handleInputNotValidException(InputNotValidException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(BindException ex) {
        return new ResponseEntity<>(ResourceBundle.getBundle("messages").getString("1001"), HttpStatus.BAD_REQUEST);
    }
}
