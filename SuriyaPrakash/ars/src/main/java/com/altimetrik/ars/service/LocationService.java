package com.altimetrik.ars.service;

import com.altimetrik.ars.model.Location;

import java.util.List;

public interface LocationService {
    Location getLocationById(Long locationId);
    Location getLocationByName(String locationName);
    Location saveLocation(Location location);

    List<Location> getAllLocations();
}
