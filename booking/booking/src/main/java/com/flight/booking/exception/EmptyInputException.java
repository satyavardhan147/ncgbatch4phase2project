package com.flight.booking.exception;

public class EmptyInputException extends RuntimeException{

    public EmptyInputException(String message)
    {
        super(message);
    }
}
