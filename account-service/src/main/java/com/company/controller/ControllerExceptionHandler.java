package com.company.controller;

import com.company.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<List<CustomErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException e) {
        // BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(fieldError.getDefaultMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString());
            errorResponse.setTimestamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorResponseList.add(errorResponse);
        }

        // Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<CustomErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        System.out.println("Ruh roh " +  e.getMessage());
        CustomErrorResponse error = new CustomErrorResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.toString());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        ResponseEntity<CustomErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }
}