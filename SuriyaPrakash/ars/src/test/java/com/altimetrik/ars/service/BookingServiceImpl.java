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
import com.altimetrik.ars.repository.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBookingById() {
        Long bookingId = 1L;
        Booking expectedBooking = new Booking();
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(expectedBooking));

        Booking result = bookingService.getBookingById(bookingId);
        assertNotNull(result);
        assertEquals(expectedBooking, result);
    }

    @Test
    void testGetBookingByIdNotFound() {
        Long bookingId = 1L;
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
        assertThrows(BookingException.class, () -> bookingService.getBookingById(bookingId));
    }

    @Test
    void testGetAllBookings() {
        List<Booking> expectedBookings = Arrays.asList(new Booking(), new Booking());
        when(bookingRepository.findAll()).thenReturn(expectedBookings);

        List<Booking> result = bookingService.getAllBookings();
        assertNotNull(result);
        assertEquals(expectedBookings.size(), result.size());
    }

    @Test
    void testCreateBooking() {
        Long customerId = 1L;
        Long flightId = 1L;
        Booking bookingToCreate = new Booking();
        Customer customer = new Customer();
        Flight flight = new Flight();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        when(bookingRepository.save(any())).thenReturn(bookingToCreate);

        Booking result = bookingService.createBooking(bookingToCreate, customerId, flightId);
        assertNotNull(result);
        assertEquals(bookingToCreate, result);
        assertEquals(customer, result.getCustomer());
        assertEquals(flight, result.getFlight());
    }

    @Test
    void testCreateBookingInvalidCustomer() {
        Long customerId = 1L;
        Long flightId = 1L;
        Booking bookingToCreate = new Booking();
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> bookingService.createBooking(bookingToCreate, customerId, flightId));
    }

    @Test
    void testCreateBookingInvalidFlight() {
        Long customerId = 1L;
        Long flightId = 1L;
        Booking bookingToCreate = new Booking();
        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());
        assertThrows(FlightException.class, () -> bookingService.createBooking(bookingToCreate, customerId, flightId));
    }

    @Test
    void testDeleteBookingById() {
        Long bookingId = 1L;
        when(bookingRepository.existsById(bookingId)).thenReturn(true);
        bookingService.deleteBookingById(bookingId);
        verify(bookingRepository, times(1)).deleteById(bookingId);
    }

    @Test
    void testDeleteBookingByIdNotFound() {
        Long bookingId = 1L;
        when(bookingRepository.existsById(bookingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> bookingService.deleteBookingById(bookingId));
    }

    @Test
    void testUpdateBookingById() {
        Long bookingId = 1L;
        Booking updatedBooking = new Booking();
        Booking existingBooking = new Booking();
        when(bookingRepository.existsById(bookingId)).thenReturn(true);
        when(bookingRepository.save(updatedBooking)).thenReturn(updatedBooking);

        Booking result = bookingService.updateBookingById(bookingId, updatedBooking);
        assertNotNull(result);
        assertEquals(updatedBooking, result);
        assertEquals(bookingId, result.getBookingId());
    }

    @Test
    void testUpdateBookingByIdNotFound() {
        Long bookingId = 1L;
        Booking updatedBooking = new Booking();
        when(bookingRepository.existsById(bookingId)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> bookingService.updateBookingById(bookingId, updatedBooking));
    }
}

