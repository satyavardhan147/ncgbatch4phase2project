package com.flight.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = NoRecordFoundException.class)
    public ResponseEntity<String> NoRecordFoundExceptionHandler(NoRecordFoundException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = EmptyInputException.class)
    public ResponseEntity<String> EmptyInputExceptionHandler(EmptyInputException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = InvalidInputDataException.class)
    public ResponseEntity<String> InvalidInputDataExceptionHandler(InvalidInputDataException ex)
    {
        String message = ex.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
       Map<String,String> map = new HashMap<>();
       ex.getBindingResult().getAllErrors().forEach((error)->{
           String filedName = ((FieldError)error).getField();
           String message = error.getDefaultMessage();
           map.put(filedName,message);
       });

       return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
}

