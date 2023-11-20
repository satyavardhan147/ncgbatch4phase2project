package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.model.Location;
import com.altimetrik.ars.service.AirlineService;
import com.altimetrik.ars.service.FlightService;
import com.altimetrik.ars.service.LocationService;
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

class FlightControllerTest {

    @Mock
    private FlightService flightService;

    @Mock
    private AirlineService airlineService;

    @Mock
    private LocationService locationService;

    @InjectMocks
    private FlightController flightController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFlightById() {
        Long flightId = 1L;
        Flight mockFlight = new Flight();
        when(flightService.getFlightById(flightId)).thenReturn(mockFlight);

        ResponseEntity<Flight> response = flightController.getFlightById(flightId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockFlight, response.getBody());
        verify(flightService, times(1)).getFlightById(flightId);
    }

    @Test
    void testRegisterFlight() {
        Flight mockFlight = new Flight();
        when(airlineService.getAirlineByName(any())).thenReturn(new Airline());
        when(locationService.getLocationByName(any())).thenReturn(new Location());
        when(flightService.registerFlight(any())).thenReturn(mockFlight);

        ResponseEntity<Flight> response = flightController.registerFlight(new Flight());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockFlight, response.getBody());
        verify(airlineService, times(1)).getAirlineByName(any());
        verify(locationService, times(2)).getLocationByName(any()); // called twice for source and destination
        verify(flightService, times(1)).registerFlight(any());
    }

    @Test
    void testUpdateFlight() {
        Long flightId = 1L;
        Flight mockUpdatedFlight = new Flight();
        when(flightService.updateFlight(eq(flightId), any())).thenReturn(mockUpdatedFlight);

        ResponseEntity<Flight> response = flightController.updateFlight(flightId, new Flight());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUpdatedFlight, response.getBody());
        verify(flightService, times(1)).updateFlight(eq(flightId), any());
    }

    @Test
    void testGetAllFlights() {
        List<Flight> mockFlights = Arrays.asList(new Flight(), new Flight());
        when(flightService.getAllFlights()).thenReturn(mockFlights);

        ResponseEntity<List<Flight>> response = flightController.getAllFlights();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockFlights, response.getBody());
        verify(flightService, times(1)).getAllFlights();
    }

    @Test
    void testSearchFlightsBySourceAndDestination() {
        String sourceLocationName = "Source";
        String destinationLocationName = "Destination";
        Location mockSourceLocation = new Location();
        Location mockDestinationLocation = new Location();
        when(locationService.getLocationByName(eq(sourceLocationName))).thenReturn(mockSourceLocation);
        when(locationService.getLocationByName(eq(destinationLocationName))).thenReturn(mockDestinationLocation);

        List<Flight> mockFlights = Arrays.asList(new Flight(), new Flight());
        when(flightService.getFlightsBySourceAndDestination(eq(mockSourceLocation), eq(mockDestinationLocation)))
                .thenReturn(mockFlights);

        ResponseEntity<List<Flight>> response = flightController.searchFlightsBySourceAndDestination(
                sourceLocationName, destinationLocationName);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockFlights, response.getBody());
        verify(locationService, times(2)).getLocationByName(any()); // called twice for source and destination
        verify(flightService, times(1)).getFlightsBySourceAndDestination(eq(mockSourceLocation), eq(mockDestinationLocation));
    }
}
