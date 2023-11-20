package com.example.flightreservation.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class FlightIdNotFoundException extends RuntimeException{
    private final int status;

    public FlightIdNotFoundException(String message, int status) {
        super(message);
        this.status = status;
    }

}