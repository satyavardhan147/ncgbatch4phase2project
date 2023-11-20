package com.flight.booking.service;

import com.flight.booking.entity.Booking;
import com.flight.booking.entity.Customer;
import com.flight.booking.entity.Flight;
import com.flight.booking.exception.NoRecordFoundException;
import com.flight.booking.repository.BookingRepository;
import com.flight.booking.repository.CustomerRepository;
import com.flight.booking.repository.FlightRepository;
import com.flight.booking.requestDTO.BookingRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {
    private BookingRepository bookingRepository;
    private CustomerRepository customerRepository;
    private FlightRepository flightRepository;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository, CustomerRepository customerRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public String addBooking(BookingRequest bookingRequest) {
        Date bookingDate = bookingRequest.getBookingDate();

        Optional<Flight> flightOptional = flightRepository.findById(bookingRequest.getFlightId());

        if (flightOptional.isEmpty())
            throw new NoRecordFoundException("No Flight exists with id" + bookingRequest.getFlightId());

        Flight flight = flightOptional.get();
        Optional<Customer> customerOptional = customerRepository.findById(bookingRequest.getCustomerId());

        if (customerOptional.isEmpty())
            throw new NoRecordFoundException("No Customer exists with id" + bookingRequest.getCustomerId());
        Customer customer = customerOptional.get();
        int price = bookingRequest.getPrice();

        int seatsBook = bookingRequest.getSeatsBook();

        Date departureDate = bookingRequest.getDepartureDate();

        Booking booking = new Booking(bookingDate, flight, customer, price, seatsBook, departureDate);

        flight.setBookingList(booking);
        customer.setBookingList(booking);
        flightRepository.save(flight);
        customerRepository.save(customer);

        return "Ticket Booked Succesfully";
    }

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();

    }

    public Booking getBookingById(int id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);

        if (bookingOptional.isEmpty()) {
            throw new NoRecordFoundException("No record Found for id" + id);

        }

        return bookingOptional.get();
    }
}
