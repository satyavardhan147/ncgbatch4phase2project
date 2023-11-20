package com.example.flightreservation.service;

import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.exception.FlightIdNotFoundException;
import com.example.flightreservation.exception.FlightNotFoundException;
import com.example.flightreservation.requestdto.FlightRequest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IFlightService{

    String flightRegistration(FlightRequest flightRequest);

    List<Flight> searchBySourceAndDestinationAndDepartureDate(String source, String destination, Date date) throws FlightNotFoundException;

    Optional<Flight> getFlightById(int id) throws FlightIdNotFoundException;
}
