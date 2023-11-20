package com.example.flightreservation.service;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.Customer;
import com.example.flightreservation.exception.CustomerIdNotFoundException;
import com.example.flightreservation.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }
    public boolean bookFlight(Booking booking) {
        bookingRepository.save(booking);
        return true;
    }
    public List<Customer> getCustomerBookings(Long customerId) throws CustomerIdNotFoundException {
            List<Booking> bookings = bookingRepository.findByCustomerId(customerId);
        return bookings.stream()
                .filter(Objects::nonNull)
                .map(Booking::getCustomer)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

    }

    }