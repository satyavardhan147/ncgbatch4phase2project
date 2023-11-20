package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.AirlineException;
import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.repository.AirlineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    private final Logger logger = LoggerFactory.getLogger(AirlineServiceImpl.class);

    @Autowired
    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Airline getAirlineById(Long airlineId) {
        logger.debug("Getting airline by ID: {}", airlineId);
        return airlineRepository.findById(airlineId).orElseThrow(()-> new AirlineException("Invalid Airline ID."));
    }

    @Override
    public Airline getAirlineByName(String airlineName) {
        logger.debug("Getting airline by name: {}", airlineName);

        Airline airline = airlineRepository.findByName(airlineName);

        if (airline != null) {
            return airline;
        } else {
            logger.error("Airline not found with name: {}", airlineName);
            throw new AirlineException("Airline not found with name: " + airlineName);
        }
    }

    @Override
    public Airline saveAirline(Airline airline) {
            logger.debug("Saving airline: {}", airline);
            Airline existingAirline = airlineRepository.findByName(airline.getName());
            if (existingAirline != null) {
                throw new DataIntegrityViolationException("Airline with name " + airline.getName() + " already exists");
            }
            return airlineRepository.save(airline);
    }

    @Override
    public List<Airline> getAllAirlines() {
            logger.debug("Getting all airlines");
            return airlineRepository.findAll();
    }

    @Override
    public void deleteAirlineById(Long airlineId) {
        logger.debug("Deleting airline by ID: {}", airlineId);

        if (airlineRepository.existsById(airlineId)) {
            airlineRepository.deleteById(airlineId);
        } else {
            logger.error("Airline not found with ID: {}", airlineId);
            throw new AirlineException("Airline not found with ID: " + airlineId);
        }
    }

}
