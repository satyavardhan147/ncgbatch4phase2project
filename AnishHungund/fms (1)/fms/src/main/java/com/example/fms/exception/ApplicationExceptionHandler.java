package com.example.fms.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handlerNoSuchElementException(Exception ex) {
        ApiErrorMessage api = new ApiErrorMessage(new Date(), "CustomerID not available", "Enter A valid CustomerID");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(CustomerNotFoundError.class)
    public ResponseEntity<?> handlerCustomerIdNotFoundException(Exception ex) {
        ApiErrorMessage api = new ApiErrorMessage(new Date(), ex.getMessage(), "Enter A valid CustomerID");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    @ExceptionHandler(FlightIdNotFound.class)
    public ResponseEntity<?> handlerFlightIdNotFoundException(Exception ex){
        ApiErrorMessage api = new ApiErrorMessage(new Date(),ex.getMessage(), "Check your flight id!! Enter valid flight Id");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(FlightNotFound.class)
    public ResponseEntity<?> handlerFlightNotFoundException(Exception ex){
        ApiErrorMessage api = new ApiErrorMessage(new Date(),ex.getMessage(), "flight not found!! Enter valid flight Id");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidationException(Exception ex, BindingResult br){
        List<FieldError> list = br.getFieldErrors();
        StringBuilder sb= new StringBuilder();
        for (FieldError fe:list) {
            sb.append(fe.getField()+" "+ fe.getDefaultMessage()+",");
        }
        ApiErrorMessage api = new ApiErrorMessage(new Date(),sb.toString(), "Check your json input data");
        ResponseEntity<?> responseEntity = new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

}