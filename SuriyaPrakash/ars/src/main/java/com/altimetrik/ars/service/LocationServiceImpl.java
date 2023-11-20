package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.LocationException;
import com.altimetrik.ars.model.Location;
import com.altimetrik.ars.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location getLocationById(Long locationId) {
            logger.debug("Getting location by ID: {}", locationId);
            return locationRepository.findById(locationId).orElseThrow(()-> new LocationException("Invalid Location Id."));
    }

    @Override
    public Location getLocationByName(String locationName) {
        logger.debug("Getting location by name: {}", locationName);

        Location location = locationRepository.findByLocationName(locationName);
        if (location == null) {
            logger.error("Location not found with name: {}", locationName);
            throw new IllegalArgumentException("Location not found with name: " + locationName);
        }
        return location;
    }

    @Override
    public Location saveLocation(Location location) {
        Location existingLocation = locationRepository.findByLocationName(location.getLocationName());

        if (existingLocation != null && location.getLocationName().equals(existingLocation.getLocationName())) {
            throw new LocationException("Location with name " + location.getLocationName() + " already exists");
        }

        logger.info("Location added successfully: {}", location);
        return locationRepository.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
            logger.debug("Getting all locations");
            return locationRepository.findAll();
        }
}
