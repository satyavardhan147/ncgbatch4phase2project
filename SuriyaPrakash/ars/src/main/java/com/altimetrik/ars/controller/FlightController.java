package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.model.Location;
import com.altimetrik.ars.service.AirlineService;
import com.altimetrik.ars.service.FlightService;
import com.altimetrik.ars.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;
    private final AirlineService airlineService;
    private final LocationService locationService;

    @Autowired
    public FlightController(FlightService flightService, AirlineService airlineService, LocationService locationService) {
        this.flightService = flightService;
        this.airlineService = airlineService;
        this.locationService = locationService;
    }

    @GetMapping("/getById")
    public ResponseEntity<Flight> getFlightById(@RequestParam Long id) {
            Flight flight = flightService.getFlightById(id);
            return new ResponseEntity<>(flight,HttpStatus.FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Flight> registerFlight(@RequestBody Flight flight) {
            Airline airline = airlineService.getAirlineByName(flight.getAirlineName());
            Location sourceLocation = locationService.getLocationByName(flight.getSourceLocationName());
            Location destinationLocation = locationService.getLocationByName(flight.getDestinationLocationName());

            flight.setAirline(airline);
            flight.setSource(sourceLocation);
            flight.setDestination(destinationLocation);

            Flight flight1 = flightService.registerFlight(flight);
            return new ResponseEntity<>(flight1,HttpStatus.CREATED);
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelFlight(@RequestParam Long id) {
        flightService.cancelFlight(id);
        return ResponseEntity.ok("Flight Canceled Successfully");
    }

    @PutMapping("/update")
    public ResponseEntity<Flight> updateFlight(@RequestParam Long id, @RequestBody Flight updatedFlight) {
            Flight flight = flightService.updateFlight(id, updatedFlight);
            return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
            List<Flight> flights = flightService.getAllFlights();
            return new ResponseEntity<>(flights, HttpStatus.FOUND);
    }

    @GetMapping("/searchBySourceAndDestination")
    public ResponseEntity<List<Flight>> searchFlightsBySourceAndDestination(
            @RequestParam String sourceLocationName,
            @RequestParam String destinationLocationName) {

            Location sourceLocation = locationService.getLocationByName(sourceLocationName);
            Location destinationLocation = locationService.getLocationByName(destinationLocationName);

            List<Flight> flights = flightService.getFlightsBySourceAndDestination(sourceLocation, destinationLocation);
            return new ResponseEntity<>(flights, HttpStatus.FOUND);
    }
}
