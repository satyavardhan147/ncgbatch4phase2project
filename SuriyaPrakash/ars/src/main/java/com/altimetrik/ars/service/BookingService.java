package com.altimetrik.ars.service;

import com.altimetrik.ars.model.Booking;

import java.util.List;

public interface BookingService {
    Booking getBookingById(Long bookingId);
    List<Booking> getAllBookings();
    Booking createBooking(Booking booking, Long customerId, Long flightId);
    void deleteBookingById(Long bookingId);
    Booking updateBookingById(Long bookingId, Booking updatedBooking);
}
