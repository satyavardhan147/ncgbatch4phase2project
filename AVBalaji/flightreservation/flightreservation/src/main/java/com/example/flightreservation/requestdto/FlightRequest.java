package com.example.flightreservation.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class FlightRequest {

    private int totalSeats;

    private String source;

    private String destination;

    private String departureTime;

    private int fare;

    private int availableSeats;

    private Date departureDate;

    private String airlineName;


    public FlightRequest()
    {
      new FlightRequest();
    }

}
