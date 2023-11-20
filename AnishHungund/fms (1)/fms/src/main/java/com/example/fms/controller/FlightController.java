package com.example.fms.controller;

import com.example.fms.entity.FlightMaster;
import com.example.fms.entity.LocationMaster;
import com.example.fms.service.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/flightApi")
public class FlightController {
    @Autowired
    private IFlightService flightService;
    @GetMapping("/search")
    public ResponseEntity<List<FlightMaster>> searchFlights(
            @RequestParam LocationMaster source,
            @RequestParam LocationMaster destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date)
    {
        List<FlightMaster> availableFlights = flightService.searchAvailableFlights(source, destination, date);
        if (availableFlights.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(availableFlights);
        }
    }
}
