package com.example.flightreservation.service;

import com.example.flightreservation.entity.Location;
import com.example.flightreservation.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}