package com.example.fms.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
@Data
@AllArgsConstructor
public class ApiErrorMessage {
    private Date on;
    private String message;
    private String cause;
}
