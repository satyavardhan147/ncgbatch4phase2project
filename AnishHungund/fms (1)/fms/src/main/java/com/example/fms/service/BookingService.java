package com.example.fms.service;

import com.example.fms.entity.BookingMaster;
import com.example.fms.exception.CustomerNotFoundError;
import com.example.fms.repository.IBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IBookingRepository bookingRepository;
    public boolean bookFlight(BookingMaster booking) {
        bookingRepository.save(booking);
        return true;
    }
    public List<BookingMaster> getCustomerBookings(Long customerId) throws CustomerNotFoundError {

        return bookingRepository.findByCustomerId(customerId);
    }
}
