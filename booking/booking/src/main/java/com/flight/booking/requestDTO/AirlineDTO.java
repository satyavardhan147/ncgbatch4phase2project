package com.flight.booking.requestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

public class AirlineDTO
{
    @NotEmpty(message = "airlineName field is Empty")
    private String airlineName;

    @NotEmpty
    @NotBlank
    private Date dateOfOperation;

    public AirlineDTO() {
    }

    public AirlineDTO(String airlineName, Date dateOfOperation) {
        this.airlineName = airlineName;
        this.dateOfOperation = dateOfOperation;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }
}
