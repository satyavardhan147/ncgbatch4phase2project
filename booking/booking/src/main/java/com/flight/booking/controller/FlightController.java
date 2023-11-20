package com.flight.booking.controller;




import com.flight.booking.entity.Flight;
import com.flight.booking.exception.EmptyInputException;
import com.flight.booking.exception.InvalidInputDataException;
import com.flight.booking.exception.NoRecordFoundException;
import com.flight.booking.requestDTO.FlightRequest;
import com.flight.booking.responseDTO.FlightResponse;
import com.flight.booking.service.FlightService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight-api")
public class FlightController
{
    Logger logger = LoggerFactory.getLogger(FlightController.class);
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/register-flight")
    public String registerFlight(@Valid @RequestBody FlightRequest flightRequest)
    {
        if(flightRequest == null)
        {
            logger.error("Filed is Empty");
            throw new EmptyInputException("Input is Empty , Please Enter Valid Input");
        }
        String str = flightService.flightRegistration(flightRequest);
        logger.info("flight registration succesfull");
        return str;
    }

    @GetMapping("/find-flight-by-destination-source-departure")
    public List<FlightResponse> searchBySourceAndDestinationAndDepartureDate(@RequestParam("source") String source , @RequestParam("destination") String destination , @RequestParam("departure")Date date)
    {
        if(destination == null || destination.length() == 0)
            throw new EmptyInputException("Destination field is Empty , Please Enter Destination");

        if(source == null || source.length() == 0)
            throw new EmptyInputException("Source field is Empty , Please Enter Source");

        List<Flight> flightList = flightService.searchBySourceAndDestinationAndDepartureDate(source,destination,date);
        List<FlightResponse> list = new ArrayList<>();

        if(flightList.isEmpty())
        {
            throw new NoRecordFoundException("No Flight Data Exists");
        }

        for(Flight flight : flightList)
        {
            int totalSeats = flight.getTotalSeats();

            String src = flight.getSource();

            String des = flight.getDestination();

            String departureTime = flight.getDepartureTime();

            int fare = flight.getFare();

            int availableSeats = flight.getAvailableSeats();

            Date departureDate = flight.getDepartureDate();

            String  airlineName = flight.getAirline().getName();

            Date dateOfOperation = flight.getAirline().getDateOfOperation();

            list.add(new FlightResponse(totalSeats,src,des,departureTime,fare,availableSeats,departureDate,airlineName,dateOfOperation));
        }
        return list;
    }

    @GetMapping("/find-flight-by-id")
    public FlightResponse getFlightById(@RequestParam("id") int id)
    {
        if(id <= 0)
            throw new InvalidInputDataException("id Field is Negative or Zero, Please Enter Positive value for Id");

        Optional<Flight> flightObject = flightService.getFlightById(id);

        if(flightObject.isEmpty())
        {

            throw new NoRecordFoundException("No Flight Data exists for given input");
        }

        Flight flight = flightObject.get();

        int totalSeats = flight.getTotalSeats();

        String src = flight.getSource();

        String des = flight.getDestination();

        String departureTime = flight.getDepartureTime();

        int fare = flight.getFare();

        int availableSeats = flight.getAvailableSeats();

        Date departureDate = flight.getDepartureDate();

        String  airlineName = flight.getAirline().getName();

        Date dateOfOperation = flight.getAirline().getDateOfOperation();

        return new FlightResponse(totalSeats,src,des,departureTime,fare,availableSeats,departureDate,airlineName,dateOfOperation);
    }
}
