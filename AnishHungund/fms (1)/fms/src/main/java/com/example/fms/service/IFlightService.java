package com.example.fms.service;

import com.example.fms.entity.FlightMaster;
import com.example.fms.entity.LocationMaster;

import java.sql.Date;
import java.util.List;

public interface IFlightService {
    public List<FlightMaster> searchAvailableFlights(LocationMaster source, LocationMaster destination, Date date);
}
