package com.example.fms.service;

import com.example.fms.entity.AirlineMaster;

import java.util.List;

public interface IAirlineService {
    public List<AirlineMaster> getAllAirlines();

    List<AirlineMaster> getAllAirlines(Long airlineId);
}
