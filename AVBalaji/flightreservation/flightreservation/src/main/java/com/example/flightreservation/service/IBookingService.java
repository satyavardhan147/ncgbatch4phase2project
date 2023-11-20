package com.example.flightreservation.service;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.Customer;
import com.example.flightreservation.exception.CustomerIdNotFoundException;

import java.util.List;
public interface IBookingService {
    boolean bookFlight(Booking booking);
    List<Customer> getCustomerBookings(Long customerId) throws CustomerIdNotFoundException;
}
