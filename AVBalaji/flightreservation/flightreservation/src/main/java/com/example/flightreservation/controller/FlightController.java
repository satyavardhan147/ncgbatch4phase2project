package com.example.flightreservation.controller;
import com.example.flightreservation.entity.Flight;
import com.example.flightreservation.exception.EmptyInputException;
import com.example.flightreservation.exception.NoRecordFoundException;
import com.example.flightreservation.requestdto.FlightRequest;
import com.example.flightreservation.responsedto.FlightResponse;
import com.example.flightreservation.service.IFlightService;
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
    private final IFlightService flightService;

    @Autowired
    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/register")
    public String flightRegister(@RequestBody FlightRequest flightRequest)
    {
        if(flightRequest == null)
        {
            throw new EmptyInputException("Input is Empty , Please Enter Valid Input");
        }
        return flightService.flightRegistration(flightRequest);
    }

    @GetMapping("/findBy-source-destination-departureDate")
    public List<FlightResponse> searchBySourceAndDestinationAndDepartureDate(@RequestParam(name = "source") String source , @RequestParam(name = "destination") String destination , @RequestParam(name = "departureDate")Date date)
    {
        if(destination == null || destination.isEmpty())
            throw new EmptyInputException("Please Enter Destination");

        if(source == null || source.isEmpty())
            throw new EmptyInputException("Please Enter Source");

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

    @GetMapping("/findBy-id")
    public FlightResponse getFlightById(@RequestParam("id") int id)
    {
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
