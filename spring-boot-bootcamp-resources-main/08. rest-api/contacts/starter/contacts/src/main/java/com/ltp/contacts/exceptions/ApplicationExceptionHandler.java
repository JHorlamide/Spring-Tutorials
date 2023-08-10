package com.ltp.contacts.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Object> handleContactNotFoundException(ContactNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(exception.getMessage()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errorMessages = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(new ErrorResponse(errorMessages), HttpStatus.BAD_REQUEST);
    }
}
