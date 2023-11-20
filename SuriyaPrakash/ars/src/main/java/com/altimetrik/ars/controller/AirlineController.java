package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

    private final AirlineService airlineService;

    @Autowired
    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping("/id")
    public ResponseEntity<Airline> getAirlineById(@RequestParam(name = "id") Long id) {
        Airline airline = airlineService.getAirlineById(id);
        return ResponseEntity.ok(airline);
    }

    @GetMapping("/name")
    public ResponseEntity<Airline> getAirlineByName(@RequestParam(name = "name") String name) {
        Airline airline = airlineService.getAirlineByName(name);
        return ResponseEntity.ok(airline);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airline>> getAllAirlines() {
        List<Airline> airlines = airlineService.getAllAirlines();
        return ResponseEntity.ok(airlines);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveAirline(@RequestBody Airline airline) {
            airlineService.saveAirline(airline);
            return ResponseEntity.ok("Airline added successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAirlineById(@RequestParam Long id) {
            airlineService.deleteAirlineById(id);
            return ResponseEntity.ok("Airline with ID " + id + " deleted successfully");
    }
}