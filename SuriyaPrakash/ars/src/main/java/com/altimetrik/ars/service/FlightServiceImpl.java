package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.FlightException;
import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.model.Location;
import com.altimetrik.ars.repository.AirlineRepository;
import com.altimetrik.ars.repository.FlightRepository;
import com.altimetrik.ars.repository.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final LocationRepository locationRepository;
    private final AirlineRepository airlineRepository;
    private final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository, LocationRepository locationRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.locationRepository = locationRepository;
        this.airlineRepository = airlineRepository;
    }

    @Override
    public Flight getFlightById(Long flightId) {
        logger.debug("Getting flight by ID: {}", flightId);
        return flightRepository.findById(flightId).orElseThrow(()-> new FlightException("Invalid flight Id."));
    }

    @Override
    public Flight registerFlight(Flight flight) {
            validateFlightForRegistration(flight);
            logger.debug("Registering flight: {}", flight);
            Airline airline = airlineRepository.findByName(flight.getAirlineName());
            if (airline == null) {
                throw new FlightException("Airline with name " + flight.getAirlineName() + " not found. Please enter a valid airline.");
            }
            Location sourceLocation = locationRepository.findByLocationName(flight.getSourceLocationName());
            if (sourceLocation == null) {
                throw new FlightException("Location with name " + flight.getSourceLocationName() + " not found. Please enter a valid source location.");
            }
            Location destinationLocation = locationRepository.findByLocationName(flight.getDestinationLocationName());
            if (destinationLocation == null) {
                throw new FlightException("Location with name " + flight.getDestinationLocationName() + " not found. Please enter a valid destination location.");
            }
            flight.setAirline(airline);
            flight.setSource(sourceLocation);
            flight.setDestination(destinationLocation);
            return flightRepository.save(flight);
    }

    @Override
    public String cancelFlight(Long flightId) {
            logger.debug("Cancelling flight by ID: {}", flightId);
            flightRepository.deleteById(flightId);
            return "Flight Canceled Successfully";
    }

    @Override
    public Flight updateFlight(Long flightId, Flight updatedFlight) {
            validateFlightForUpdate(updatedFlight);
            Flight existingFlight = flightRepository.findById(flightId)
                    .orElseThrow(() -> new FlightException("Flight not found with ID: " + flightId));

            existingFlight.setAirline(updatedFlight.getAirline());
            existingFlight.setSource(updatedFlight.getSource());
            existingFlight.setDestination(updatedFlight.getDestination());
            return flightRepository.save(existingFlight);
    }

    @Override
    public List<Flight> getAllFlights() {
            logger.debug("Getting all flights");
            return flightRepository.findAll();
    }
    @Override
    public List<Flight> getFlightsBySourceAndDestination(Location sourceLocation, Location destinationLocation) {
        logger.debug("Getting flights by source and destination: {} -> {}", sourceLocation, destinationLocation);
        if (sourceLocation == null || destinationLocation == null) {
            logger.error("Invalid source or destination location. Source: {}, Destination: {}", sourceLocation, destinationLocation);
            throw new IllegalArgumentException("Invalid source or destination location. Please provide valid source and destination.");
        }
        Location source = locationRepository.findByLocationName(sourceLocation.getLocationName());
        Location destination = locationRepository.findByLocationName(destinationLocation.getLocationName());

        if (source == null || destination == null) {
            logger.error("Location not found. Source: {}, Destination: {}", source, destination);
            throw new IllegalArgumentException("Location not found. Please enter valid source and destination locations.");
        }
        return flightRepository.findBySourceAndDestination(source, destination);
    }


    void validateFlightForRegistration(Flight flight) {
        if (flight == null || flight.getAirline() == null || flight.getSource() == null || flight.getDestination() == null) {
            logger.error("Flight validation failed. Details: Airline={}, Source={}, Destination={}",
                    flight != null ? flight.getAirline() : "null",
                    flight != null ? flight.getSource() : "null",
                    flight != null ? flight.getDestination() : "null");
            throw new IllegalArgumentException("Flight details are incomplete for registration. Please provide valid airline, source, and destination.");
        }
    }

    void validateFlightForUpdate(Flight updatedFlight) {
        if (updatedFlight == null || Objects.isNull(updatedFlight.getFlightId())) {
            throw new IllegalArgumentException("Invalid Flight details for update");
        }
    }
}
