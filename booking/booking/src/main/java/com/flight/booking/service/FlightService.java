package com.flight.booking.service;


import com.flight.booking.entity.Flight;
import com.flight.booking.requestDTO.FlightRequest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService
{
    public String flightRegistration(FlightRequest flightRequest);
    public List<Flight> searchBySourceAndDestinationAndDepartureDate(String source , String destination , Date date);
    public Optional<Flight> getFlightById(int id);
}
