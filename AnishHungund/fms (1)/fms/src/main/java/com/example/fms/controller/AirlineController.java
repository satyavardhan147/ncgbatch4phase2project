package com.example.fms.controller;

import com.example.fms.entity.AirlineMaster;
import com.example.fms.service.IAirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airlineApi")
public class AirlineController {
    @Autowired
    private IAirlineService airlineService;

    @GetMapping("/all")
    public ResponseEntity<List<AirlineMaster>> getAllAirlines(Long airlineId) {
        List<AirlineMaster> airlines = airlineService.getAllAirlines();
        if (airlines.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(airlines);
        }
    }
}