package com.altimetrik.ars.service;

import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.model.Location;

import java.util.List;

public interface FlightService {
    Flight getFlightById(Long flightId);

    Flight registerFlight(Flight flight);

    String cancelFlight(Long flightId);

    Flight updateFlight(Long flightId, Flight updatedFlight);

    List<Flight> getAllFlights();

    List<Flight> getFlightsBySourceAndDestination(Location sourceLocation, Location destinationLocation);
}
