package com.example.fms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundError.class)
    public ResponseEntity<ApiErrorMessage> handleCustomerIdNotFoundException(CustomerNotFoundError ex) {
        ApiErrorMessage apiError = new ApiErrorMessage(new Date(), ex.getMessage(), "Customer ID Incorrect! Enter a valid customer Id");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
