package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Booking;
import com.altimetrik.ars.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBookings() {
        List<Booking> mockBookings = Arrays.asList(new Booking(), new Booking());
        when(bookingService.getAllBookings()).thenReturn(mockBookings);

        ResponseEntity<List<Booking>> response = bookingController.getAllBookings();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockBookings, response.getBody());
        verify(bookingService, times(1)).getAllBookings();
    }

    @Test
    void testCreateBooking() {
        Booking mockBooking = new Booking();
        Long customerId = 1L;
        Long flightId = 2L;
        when(bookingService.createBooking(any(), eq(customerId), eq(flightId))).thenReturn(mockBooking);

        ResponseEntity<Booking> response = bookingController.createBooking(new Booking(), customerId, flightId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockBooking, response.getBody());
        verify(bookingService, times(1)).createBooking(any(), eq(customerId), eq(flightId));
    }

    @Test
    void testDeleteBookingById() {
        Long bookingId = 1L;
        doNothing().when(bookingService).deleteBookingById(bookingId);

        ResponseEntity<String> response = bookingController.deleteBookingById(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Booking with ID " + bookingId + " deleted successfully", response.getBody());
        verify(bookingService, times(1)).deleteBookingById(bookingId);
    }

    @Test
    void testGetBookingById() {
        Long bookingId = 1L;
        Booking mockBooking = new Booking();
        when(bookingService.getBookingById(bookingId)).thenReturn(mockBooking);

        ResponseEntity<Booking> response = bookingController.getBookingById(bookingId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockBooking, response.getBody());
        verify(bookingService, times(1)).getBookingById(bookingId);
    }

    @Test
    void testUpdateBookingById() {
        Long bookingId = 1L;
        Booking mockBooking = new Booking();
        when(bookingService.updateBookingById(eq(bookingId), any())).thenReturn(mockBooking);

        ResponseEntity<Booking> response = bookingController.updateBookingById(bookingId, new Booking());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockBooking, response.getBody());
        verify(bookingService, times(1)).updateBookingById(eq(bookingId), any());
    }
}

