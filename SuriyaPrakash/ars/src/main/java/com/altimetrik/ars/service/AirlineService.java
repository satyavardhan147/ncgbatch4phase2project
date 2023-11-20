package com.altimetrik.ars.service;

import com.altimetrik.ars.model.Airline;

import java.util.List;

public interface AirlineService {
    Airline getAirlineById(Long airlineId);
    Airline getAirlineByName(String airlineName);
    Airline saveAirline(Airline airline);
    List<Airline> getAllAirlines();
    void deleteAirlineById(Long airlineId);
}
