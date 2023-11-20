package com.flight.booking.responseDTO;

import java.sql.Date;

public class AirlineRespose {
    private String name;

    private Date dateOfOperation;

    public AirlineRespose() {
    }

    public AirlineRespose(String name, Date dateOfOperation) {
        this.name = name;
        this.dateOfOperation = dateOfOperation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }
}

