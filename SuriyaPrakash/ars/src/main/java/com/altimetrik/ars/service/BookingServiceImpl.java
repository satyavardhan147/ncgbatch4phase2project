package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.BookingException;
import com.altimetrik.ars.exception.CustomerException;
import com.altimetrik.ars.exception.FlightException;
import com.altimetrik.ars.model.Booking;
import com.altimetrik.ars.model.Customer;
import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.repository.BookingRepository;
import com.altimetrik.ars.repository.CustomerRepository;
import com.altimetrik.ars.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Booking getBookingById(Long bookingId) {
            logger.debug("Getting booking by ID: {}", bookingId);
        return bookingRepository.findById(bookingId).orElseThrow(()-> new BookingException("Booking ID is Invalid"));
    }

    @Override
    public List<Booking> getAllBookings() {
            logger.debug("Getting all bookings");
            return bookingRepository.findAll();
    }

    @Override
    public Booking createBooking(Booking booking, Long customerId, Long flightId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerException("Customer ID is Invalid"));
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightException("Flight Id is invalid"));
        booking.setCustomer(customer);
        booking.setFlight(flight);
        booking.setPrice((int) (booking.getSeatsBooked()*flight.getFare()));
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBookingById(Long bookingId) {
        logger.debug("Deleting booking by ID: {}", bookingId);
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.deleteById(bookingId);
        } else {
            logger.error("Booking not found with ID: {}", bookingId);
            throw new IllegalArgumentException("Booking not found with ID: " + bookingId);
        }
    }

    @Override
    public Booking updateBookingById(Long bookingId, Booking updatedBooking) {
        logger.debug("Updating booking by ID: {}", bookingId);

        if (bookingRepository.existsById(bookingId)) {
            updatedBooking.setBookingId(bookingId);
            return bookingRepository.save(updatedBooking);
        } else {
            logger.error("Booking not found with ID: {}", bookingId);
            throw new IllegalArgumentException("Booking not found with ID: " + bookingId);
        }
    }
}
