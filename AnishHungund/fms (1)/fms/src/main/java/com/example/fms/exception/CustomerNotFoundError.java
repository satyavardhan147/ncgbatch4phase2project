package com.example.fms.exception;

import lombok.Data;
@Data
public class CustomerNotFoundError extends RuntimeException{
    String msg;
    public CustomerNotFoundError(String message)
    {
        super(message);
        this.msg=message;
    }
}
