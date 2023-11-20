package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Booking;
import com.altimetrik.ars.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
            List<Booking> bookings = bookingService.getAllBookings();
            return new ResponseEntity<>(bookings, HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @RequestParam Long customerId, @RequestParam Long flightId) {
        Booking booking1 = bookingService.createBooking(booking, customerId, flightId);
        return new ResponseEntity<>(booking1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookingById(@RequestParam Long bookingId) {
            bookingService.deleteBookingById(bookingId);
            return ResponseEntity.ok("Booking with ID " + bookingId + " deleted successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<Booking> getBookingById(@RequestParam Long bookingId) {
            Booking booking = bookingService.getBookingById(bookingId);
            return new ResponseEntity<>(booking, HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Booking> updateBookingById(
            @RequestParam Long bookingId,
            @RequestBody Booking updatedBooking) {
            Booking booking = bookingService.updateBookingById(bookingId, updatedBooking);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}
