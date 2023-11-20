package com.flight.booking.service;


import com.flight.booking.entity.Airline;
import com.flight.booking.entity.Flight;
import com.flight.booking.exception.InvalidInputDataException;
import com.flight.booking.repository.AirlineRepository;
import com.flight.booking.repository.FlightRepository;
import com.flight.booking.requestDTO.FlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImp implements FlightService
{

    private FlightRepository flightRepository;

    private AirlineRepository airlineRepository;

    @Autowired
    public FlightServiceImp(FlightRepository flightRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
    }

    public String flightRegistration(FlightRequest flightRequest)
    {
        Flight flight = null;

        Optional<Airline> airline = airlineRepository.findByName(flightRequest.getAirlineName());

        if(airline.isEmpty())
        {
          // throw exception airlines record not in database
            throw new InvalidInputDataException("AirLine Not present please Enter Valid Airline Name");
        }
        else {
            flight = new Flight(flightRequest.getTotalSeats(), flightRequest.getSource(), flightRequest.getDestination(), flightRequest.getDepartureTime(), flightRequest.getFare(), flightRequest.getAvailableSeats(), flightRequest.getDepartureDate() ,airline.get());
        }

        airline.get().setFlightList(flight);

        airlineRepository.save(airline.get());
        return "Flight Details saved succesfully";
    }

    public List<Flight> searchBySourceAndDestinationAndDepartureDate(String source , String destination , Date date)
    {
        return flightRepository.findBySourceAndDestinationAndDepartureDate(source,destination,date);
    }

    public Optional<Flight> getFlightById(int id) {

        return flightRepository.findById(id);

    }
}
