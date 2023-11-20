package com.example.flightreservation.responsedto;
import lombok.Getter;

import java.sql.Date;
@Getter
public class AirlineResponse {
    private String name;

    private final Date dateOfOperation;

    public AirlineResponse(String name, Date dateOfOperation) {
        this.name = name;
        this.dateOfOperation = dateOfOperation;
    }

    public void setName(String name) {
        this.name = name;
    }
}