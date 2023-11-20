package com.example.fms.exception;

import lombok.Data;

@Data
public class FlightNotFound extends RuntimeException {
    String msg;

    public FlightNotFound(String message) {
        super(message);
        this.msg = message;
    }
}
