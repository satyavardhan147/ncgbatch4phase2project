package com.flight.booking.service;

import com.flight.booking.entity.Airline;
import com.flight.booking.exception.InvalidInputDataException;
import com.flight.booking.repository.AirlineRepository;
import com.flight.booking.requestDTO.AirlineDTO;
import com.flight.booking.responseDTO.AirlineRespose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirlineServiceImp implements AirlineService
{

    private AirlineRepository airlineRepository;

    @Autowired
    public AirlineServiceImp(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public String addAirline(AirlineDTO airlineDTO)
    {
        Airline airline = new Airline(airlineDTO.getAirlineName(),airlineDTO.getDateOfOperation());
        airlineRepository.save(airline);
        return "Airline Added Succesfully";
    }



    public AirlineRespose getAirlineById(int id)
    {
        Airline airline = null;
        Optional<Airline> airlineObject = airlineRepository.findById(id);
        if(airlineObject.isPresent()) {
            airline = airlineObject.get();
            return new AirlineRespose(airline.getName(), airline.getDateOfOperation());
        }
        throw new InvalidInputDataException("No Data present for id"+id);
    }

    public String updateAirline(int id, AirlineDTO airlineDTO) {

        Airline airline = null;
        Optional<Airline> airlineObject = airlineRepository.findById(id);
        if(airlineObject.isPresent()) {
            airline = airlineObject.get();
            airline.setName(airlineDTO.getAirlineName());
            airline.setDateOfOperation(airlineDTO.getDateOfOperation());
            airlineRepository.save(airline);
            return "Airline data updated";
        }

        throw new InvalidInputDataException("No Data present for id"+id);
    }
}
