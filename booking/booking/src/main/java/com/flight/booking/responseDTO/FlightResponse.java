package com.flight.booking.responseDTO;


import java.sql.Date;

public class FlightResponse
{

    private int totalSeats;

    private String source;

    private String destination;

    private String departureTime;

    private int fare;

    private int availableSeats;
    private Date departureDate;

    private String airlineName;

    private Date dateOfOperation;

    public FlightResponse() {
    }

    public FlightResponse(int totalSeats, String source, String destination, String departureTime, int fare, int availableSeats, Date departureDate, String name, Date dateOfOperation) {
        this.totalSeats = totalSeats;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
        this.availableSeats = availableSeats;
        this.departureDate = departureDate;
        this.airlineName = name;
        this.dateOfOperation = dateOfOperation;
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

    public String getName() {
        return airlineName;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }
}
