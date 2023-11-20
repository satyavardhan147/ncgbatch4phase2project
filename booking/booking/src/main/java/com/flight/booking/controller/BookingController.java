package com.flight.booking.controller;



import com.flight.booking.entity.Booking;
import com.flight.booking.exception.InvalidInputDataException;
import com.flight.booking.exception.NoRecordFoundException;
import com.flight.booking.requestDTO.BookingRequest;
import com.flight.booking.responseDTO.BookingResponse;
import com.flight.booking.service.BookingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking-api")
public class BookingController
{
    Logger logger = LoggerFactory.getLogger(BookingController.class);
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @PostMapping("/add-booking-details")
    public ResponseEntity<String> addBooking(@Valid @RequestBody BookingRequest booking)
    {
        String message = bookingService.addBooking(booking);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(message, HttpStatus.CREATED);
        logger.info("Booking done Succesfully");
        return responseEntity;
    }

    @GetMapping("/get-all-booking-details")
    public ResponseEntity<List<BookingResponse>> getAllBookings()
    {
        List<Booking> bookingList = bookingService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();

        for(Booking booking : bookingList)
        {
            Date bookingDate = booking.getBookingDate();

            int flightId = booking.getFlight().getId();

            int customerId = booking.getCustomer().getCustomerId();

            int price = booking.getPrice();

            int seatsBook = booking.getSeatsBook();

            Date departureDate = booking.getDepartureDate();

            bookingResponses.add(new BookingResponse(bookingDate,flightId,customerId,price,seatsBook,departureDate));
        }

        return new ResponseEntity<>(bookingResponses,HttpStatus.FOUND);

    }

    @GetMapping("/get-booking-details-by-id")
    public ResponseEntity<BookingResponse> getBookingById(@RequestParam("id") int id)
    {
        if(id < 1) {
            logger.error("Invalid Input , Negative valued Entered for id");
            throw new InvalidInputDataException("Id Entered is Negative , Please Enter Positive Value");
        }
        Booking booking = bookingService.getBookingById(id);
        logger.info("Booking Id found");
        if(booking == null)
            throw new NoRecordFoundException("No Booking Record Found for id"+id);


        Date bookingDate = booking.getBookingDate();

        int flightId = booking.getFlight().getId();

        int customerId = booking.getCustomer().getCustomerId();

        int price = booking.getPrice();

        int seatsBook = booking.getSeatsBook();

        Date departureDate = booking.getDepartureDate();

        BookingResponse bookingResponse = new BookingResponse(bookingDate,flightId,customerId,price,seatsBook,departureDate);

        return new ResponseEntity<>(bookingResponse,HttpStatus.FOUND);

    }

}