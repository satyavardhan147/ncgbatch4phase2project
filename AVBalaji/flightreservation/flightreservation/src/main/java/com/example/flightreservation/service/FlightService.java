package com.example.flightreservation.service;
import com.example.flightreservation.entity.Airline;
import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.exception.EmptyInputException;
import com.example.flightreservation.repository.AirlineRepository;
import com.example.flightreservation.repository.FlightRepository;
import com.example.flightreservation.requestdto.FlightRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IFlightService
{
    private final FlightRepository flightRepository;
    private final AirlineRepository airlineRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AirlineRepository airlineRepository) {
        this.flightRepository = flightRepository;
        this.airlineRepository = airlineRepository;
    }


    public String flightRegistration(FlightRequest flightRequest) {
        Flight flight;

        Optional<Airline> airline = airlineRepository.findByName(flightRequest.getAirlineName());

        if(airline.isEmpty())
        {
           throw new EmptyInputException("Enter valid Input");
        }
        else {
            flight = new Flight(flightRequest.getTotalSeats(), flightRequest.getSource(), flightRequest.getDestination(), flightRequest.getDepartureTime(), flightRequest.getFare(), flightRequest.getAvailableSeats(), flightRequest.getDepartureDate() ,airline.get());
        }

        airline.get().addFlight(flight);

        airlineRepository.save(airline.get());
        return "Flight Details saved successfully";
    }

    public List<Flight> searchBySourceAndDestinationAndDepartureDate(String source , String destination , Date date)
    {
        return flightRepository.findBySourceAndDestinationAndDepartureDate(source,destination,date);
    }

    public Optional<Flight> getFlightById(int id) {

        return flightRepository.findById(id);
    }
}