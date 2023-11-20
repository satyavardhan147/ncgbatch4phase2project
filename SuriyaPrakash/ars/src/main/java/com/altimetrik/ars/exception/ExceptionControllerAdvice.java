package com.altimetrik.ars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),404,"No Handler Found Exception", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),500,"Validation Exception", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),500,"Runtime Exception", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),500,"Generic Exception", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AirlineException.class)
    public ResponseEntity<CustomErrorResponse> handleAirlineException(AirlineException ex){
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),404,"AirLine Operation failed", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<CustomErrorResponse> handleBookingException(BookingException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),404,"Booking Operation Failed", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomerException(CustomerException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),404,"Customer Operation Failed", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FlightException.class)
    public ResponseEntity<CustomErrorResponse> handleFlightException(FlightException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),404,"Flight Operation Failed", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LocationException.class)
    public ResponseEntity<CustomErrorResponse> handleLocationException(LocationException ex) {
        String message = ex.getMessage();
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(LocalDateTime.now().toString(),404,"Location Operation Failed", message);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
}
