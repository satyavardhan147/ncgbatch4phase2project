package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.FlightException;
import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.model.Location;
import com.altimetrik.ars.repository.AirlineRepository;
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

class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFlightById() {
        Long flightId = 1L;
        Flight expectedFlight = new Flight();
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(expectedFlight));

        Flight result = flightService.getFlightById(flightId);
        assertNotNull(result);
        assertEquals(expectedFlight, result);
    }

    @Test
    void testGetFlightByIdNotFound() {
        Long flightId = 1L;
        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());
        assertThrows(FlightException.class, () -> flightService.getFlightById(flightId));
    }

    @Test
    void testRegisterFlight() {
        Flight flightToRegister = new Flight();
        Airline airline = new Airline();
        Location source = new Location();
        Location destination = new Location();

        flightToRegister.setAirline(airline);
        flightToRegister.setSource(source);
        flightToRegister.setDestination(destination);

        when(airlineRepository.findByName(anyString())).thenReturn(airline);
        when(locationRepository.findByLocationName(anyString())).thenReturn(source, destination);
        when(flightRepository.save(any())).thenReturn(flightToRegister);

        Flight result = flightService.registerFlight(flightToRegister);
        assertNotNull(result);
        assertEquals(flightToRegister, result);
    }

    @Test
    void testCancelFlight() {
        Long flightId = 1L;
        String result = flightService.cancelFlight(flightId);
        assertEquals("Flight Canceled Successfully", result);
        verify(flightRepository, times(1)).deleteById(flightId);
    }

    @Test
    void testUpdateFlight() {
        Long flightId = 1L;
        Flight updatedFlight = new Flight();
        updatedFlight.setFlightId(flightId);

        Flight existingFlight = new Flight();
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(existingFlight));
        when(flightRepository.save(existingFlight)).thenReturn(existingFlight);

        Flight result = flightService.updateFlight(flightId, updatedFlight);
        assertNotNull(result);
        assertEquals(existingFlight, result);
    }

    @Test
    void testUpdateFlightNotFound() {
        Long flightId = 1L;
        Flight updatedFlight = new Flight();
        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());
        assertThrows(FlightException.class, () -> flightService.updateFlight(flightId, updatedFlight));
    }

    @Test
    void testGetAllFlights() {
        List<Flight> expectedFlights = Arrays.asList(new Flight(), new Flight());
        when(flightRepository.findAll()).thenReturn(expectedFlights);

        List<Flight> result = flightService.getAllFlights();
        assertNotNull(result);
        assertEquals(expectedFlights.size(), result.size());
    }

    @Test
    void testGetFlightsBySourceAndDestination() {
        Location sourceLocation = new Location();
        Location destinationLocation = new Location();
        when(locationRepository.findByLocationName(anyString())).thenReturn(sourceLocation, destinationLocation);

        List<Flight> expectedFlights = Arrays.asList(new Flight(), new Flight());
        when(flightRepository.findBySourceAndDestination(sourceLocation, destinationLocation)).thenReturn(expectedFlights);

        List<Flight> result = flightService.getFlightsBySourceAndDestination(sourceLocation, destinationLocation);
        assertNotNull(result);
        assertEquals(expectedFlights.size(), result.size());
    }

    @Test
    void testGetFlightsBySourceAndDestinationInvalidLocations() {
        Location sourceLocation = new Location();
        Location destinationLocation = new Location();
        when(locationRepository.findByLocationName(anyString())).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> flightService.getFlightsBySourceAndDestination(sourceLocation, destinationLocation));
    }
}

