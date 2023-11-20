package com.example.flightreservation.controller;

import com.example.flightreservation.requestdto.AirlineDto;
import com.example.flightreservation.responsedto.AirlineResponse;
import com.example.flightreservation.service.IAirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airline-api")
public class AirlineController {

    private final IAirlineService airlineService;
    @Autowired
    public AirlineController(IAirlineService airlineService){
        this.airlineService=airlineService;
    }
        @PostMapping("/register")
        public ResponseEntity<String> registerAirline(@RequestBody AirlineDto airlineDTO)
        {
            String message = airlineService.registerAirline(airlineDTO);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
        @GetMapping("/get-byId/{id}")
        public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable int id)
        {
            AirlineResponse airlineResponse = airlineService.getAirlineById(id);
            return new ResponseEntity<>(airlineResponse, HttpStatus.FOUND);
        }

        @PutMapping("/update")
        public ResponseEntity<String> updateAirline(@RequestParam("id") int id , @RequestBody AirlineDto airlineDTO)
        {
            String message = airlineService.updateAirline(id,airlineDTO);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
    }