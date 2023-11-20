package com.example.fms.service;

import com.example.fms.entity.LocationMaster;
import com.example.fms.repository.ILocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationService {
    private ILocationRepository locationRepository;
    public List<LocationMaster> getAllLocations() {
        return locationRepository.findAll();
    }
}
