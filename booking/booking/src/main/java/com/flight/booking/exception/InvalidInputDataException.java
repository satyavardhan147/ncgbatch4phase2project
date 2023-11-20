package com.flight.booking.exception;

public class InvalidInputDataException extends RuntimeException{

    public InvalidInputDataException(String message)
    {
        super(message);
    }
}
