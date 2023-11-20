package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Location;
import com.altimetrik.ars.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<Location> getLocationById(@RequestParam(name = "id") Long id) {
            Location location = locationService.getLocationById(id);
            return new ResponseEntity<>(location, HttpStatus.FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<Location> getLocationByName(@RequestParam(name = "name") String name) {
            Location location = locationService.getLocationByName(name);
            return new ResponseEntity<>(location, HttpStatus.FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAllLocations() {
            List<Location> locations = locationService.getAllLocations();
            return new ResponseEntity<>(locations, HttpStatus.FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
            Location location1 = locationService.saveLocation(location);
            return new ResponseEntity<>(location1, HttpStatus.CREATED);
    }
}
