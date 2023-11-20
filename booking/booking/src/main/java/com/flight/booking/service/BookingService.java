package com.flight.booking.service;


import com.flight.booking.entity.Booking;
import com.flight.booking.requestDTO.BookingRequest;

import java.util.List;

public interface BookingService
{

    public String addBooking(BookingRequest bookingRequest);

    public List<Booking> getAllBookings();
    public Booking getBookingById(int id);

}
