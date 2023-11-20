package com.example.flightreservation.service;

import com.example.flightreservation.requestdto.AirlineDto;
import com.example.flightreservation.responsedto.AirlineResponse;

public interface IAirlineService {
    String updateAirline(int id, AirlineDto airlineDTO);
    AirlineResponse getAirlineById(int id);
    String registerAirline(AirlineDto airlineDTO);
}
