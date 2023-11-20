package com.example.flightreservation.exception;
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
    public ResponseEntity<Object> handlerNoSuchElementException(Exception ex){
        ApiError api = new ApiError(new Date(),"Customer Id is not Found !!!", "Check your customer id!! Enter valid Id");
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerIdNotFoundException.class)
    public ResponseEntity<Object> handlerCustomerIdNotFoundException(Exception ex){
        ApiError api = new ApiError(new Date(),ex.getMessage(), "Check your customer id!! Enter valid customer Id");
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightIdNotFoundException.class)
    public ResponseEntity<Object> handlerFlightIdNotFoundException(Exception ex){
        ApiError api = new ApiError(new Date(),ex.getMessage(), "Check your flight id!! Enter valid flight Id");
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<Object> handlerFlightNotFoundException(Exception ex){
        ApiError api = new ApiError(new Date(),ex.getMessage(), "flight not found!! Enter valid flight Id");
        return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerValidationException(Exception ex, BindingResult br){
        List<FieldError> list = br.getFieldErrors();
        StringBuilder sb= new StringBuilder();
        for (FieldError fe:list) {
            sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage()).append(",");
        }
        ApiError api = new ApiError(new Date(),sb.toString(), "Check your json input data");
        return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
    }
}
