package com.example.fms.controller;

import com.example.fms.entity.BookingMaster;
import com.example.fms.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookingApi")
public class BookingController {
    @Autowired
    private IBookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<String> bookFlight(@RequestBody BookingMaster booking) {
        boolean isBooked = bookingService.bookFlight(booking);
        if (isBooked) {
            return ResponseEntity.ok("Flight booked successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Booking failed");
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<BookingMaster>> getCustomerBookings(@PathVariable Long customerId) {
        List<BookingMaster> customerBookings = bookingService.getCustomerBookings(customerId);
        if (customerBookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(customerBookings);
        }
    }
}
