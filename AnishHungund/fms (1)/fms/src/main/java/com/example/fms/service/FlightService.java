package com.example.fms.service;

import com.example.fms.entity.FlightMaster;
import com.example.fms.entity.LocationMaster;
import com.example.fms.repository.IFlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class FlightService implements IFlightService {
    @Autowired
    private IFlightRepository flightRepository;
    public List<FlightMaster> searchAvailableFlights(LocationMaster source, LocationMaster destination, Date date) {
        return flightRepository.findBySourceAndDestinationAndDepartDate(source, destination, date);
    }
}
