package com.example.flightreservation.service;

import com.example.flightreservation.entity.Airline;
import com.example.flightreservation.repository.AirlineRepository;
import com.example.flightreservation.requestdto.AirlineDto;
import com.example.flightreservation.responsedto.AirlineResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService implements IAirlineService {
    private final AirlineRepository airlineRepository;

    @Autowired
    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

        public String registerAirline(AirlineDto airlineDTO)
        {
            Airline airline = new Airline(airlineDTO.getAirlineName(),airlineDTO.getDateOfOperation());
            airlineRepository.save(airline);
            return "Airline Added Successfully";
        }

        public AirlineResponse getAirlineById(int id)
        {
            Airline airline = airlineRepository.findById(id).get();
            return new AirlineResponse(airline.getName(),airline.getDateOfOperation());
        }

        public String updateAirline(int id, AirlineDto airlineDTO) {

            Airline airline = airlineRepository.findById(id).get();
            airline.setName(airlineDTO.getAirlineName());
            airline.setDateOfOperation(airlineDTO.getDateOfOperation());
            airlineRepository.save(airline);
            return "Airline details were updated";
        }
    }



