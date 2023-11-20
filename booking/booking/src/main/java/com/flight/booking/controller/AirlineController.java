package com.flight.booking.controller;


import com.flight.booking.exception.InvalidInputDataException;
import com.flight.booking.requestDTO.AirlineDTO;
import com.flight.booking.responseDTO.AirlineRespose;
import com.flight.booking.service.AirlineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airline-api")
public class AirlineController
{
    Logger logger = LoggerFactory.getLogger(AirlineController.class);
    private AirlineService airlineService;
    @Autowired
    public AirlineController(AirlineService airlineService)
    {
        this.airlineService = airlineService;
    }
    @PostMapping("/register-airline")
    public ResponseEntity<String> addAirline(@RequestBody AirlineDTO airlineDTO)
    {
        String message = airlineService.addAirline(airlineDTO);
        logger.info("Airline Added Succesfully");
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }
    @GetMapping("/get-airline-by-id")
    public ResponseEntity<AirlineRespose> getAirlineById(@RequestParam("id") int id)
    {
        if(id <= 0) {
            logger.error("Invalid Input Id");
            throw new InvalidInputDataException("Id Entered is Nagative or zero, Please Enter positive value");
        }
        AirlineRespose airlineRespose = airlineService.getAirlineById(id);
        logger.info("Airline found Succesfully using Id");
        return new ResponseEntity<>(airlineRespose, HttpStatus.FOUND);

    }

    @PutMapping("/update-airline")
    public ResponseEntity<String> updateAirline(@RequestParam("id") int id , @RequestBody AirlineDTO airlineDTO)
    {
        String message = airlineService.updateAirline(id,airlineDTO);
        if(!message.equals(""))
        {
            logger.info("Airline updated succesfully");
        }
        return new ResponseEntity<>(message,HttpStatus.OK);

    }
}

