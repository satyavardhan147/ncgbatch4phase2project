package com.flight.booking.service;

import com.flight.booking.requestDTO.AirlineDTO;
import com.flight.booking.responseDTO.AirlineRespose;

public interface AirlineService {

    public String addAirline(AirlineDTO airlineDTO);

    public AirlineRespose getAirlineById(int id);
    public String updateAirline(int id, AirlineDTO airlineDTO);
}
