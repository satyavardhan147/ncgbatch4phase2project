package com.flight.booking.requestDTO;


import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public class FlightRequest {


    @Min(value = 11 ,message = "Total seats should be atleat 11")
    private int totalSeats;

    @NotEmpty(message = "Source field is Empty or null")
    private String source;

    @NotEmpty(message = "Destination field is Empty or null")
    private String destination;

    @NotEmpty(message = "Departure time field is Empty or Null")
    private String departureTime;

    @Min(value = 11 , message = "fare should be minimum 11")
    private int fare;

    @Min(value = 11 , message = "minimum available seats should be 11")
    private int availableSeats;
    @NotNull(message = "Departure date field is Empty")
    private Date departureDate;
    @NotEmpty(message = "Airline field is Empty")
    private String airlineName;


    public FlightRequest()
    {
       // No argument constructor
    }

    public String getAirlineName() {
        return airlineName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public int getFare() {
        return fare;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

}
