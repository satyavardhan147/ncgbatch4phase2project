package com.example.fms.service;

import com.example.fms.entity.BookingMaster;
import com.example.fms.exception.CustomerNotFoundError;

import java.util.List;

public interface IBookingService {
    public boolean bookFlight(BookingMaster booking);
    public List<BookingMaster> getCustomerBookings(Long customerId) throws CustomerNotFoundError;
}

