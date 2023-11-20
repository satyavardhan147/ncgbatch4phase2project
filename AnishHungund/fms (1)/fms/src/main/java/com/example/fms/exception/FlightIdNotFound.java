package com.example.fms.exception;
import lombok.Data;

@Data
    public class FlightIdNotFound extends RuntimeException{
        String msg;
        public FlightIdNotFound(String message)
        {
            super(message);
            this.msg=message;
        }
    }

