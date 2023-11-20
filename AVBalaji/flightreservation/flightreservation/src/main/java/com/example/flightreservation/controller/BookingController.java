package com.example.flightreservation.controller;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.Customer;
import com.example.flightreservation.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-api")
public class BookingController {
    private final IBookingService bookingService;
    @Autowired
    public BookingController(IBookingService bookingService){
        this.bookingService=bookingService;
    }
    @PostMapping("/book")
    public ResponseEntity<String> bookFlight(@RequestBody Booking booking) {
        boolean isBooked = bookingService.bookFlight(booking);
        if (isBooked) {
            return ResponseEntity.ok("Flight booked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking failed");
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Customer>> getCustomerBookings(@PathVariable Long customerId) {
        List<Customer> customerBookings = bookingService.getCustomerBookings(customerId);
        if (customerBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(customerBookings);
        }
    }
}

