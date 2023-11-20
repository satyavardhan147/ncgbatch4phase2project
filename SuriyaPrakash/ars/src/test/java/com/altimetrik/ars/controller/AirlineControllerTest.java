package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.service.AirlineService;
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

class AirlineControllerTest {

    @Mock
    private AirlineService airlineService;

    @InjectMocks
    private AirlineController airlineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAirlineById() {
        Long airlineId = 1L;
        Airline mockAirline = new Airline();
        when(airlineService.getAirlineById(airlineId)).thenReturn(mockAirline);

        ResponseEntity<Airline> response = airlineController.getAirlineById(airlineId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAirline, response.getBody());
        verify(airlineService, times(1)).getAirlineById(airlineId);
    }

    @Test
    void testGetAirlineByName() {
        String airlineName = "Test Airline";
        Airline mockAirline = new Airline();
        when(airlineService.getAirlineByName(airlineName)).thenReturn(mockAirline);

        ResponseEntity<Airline> response = airlineController.getAirlineByName(airlineName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAirline, response.getBody());
        verify(airlineService, times(1)).getAirlineByName(airlineName);
    }

    @Test
    void testGetAllAirlines() {
        List<Airline> mockAirlines = Arrays.asList(new Airline(), new Airline());
        when(airlineService.getAllAirlines()).thenReturn(mockAirlines);

        ResponseEntity<List<Airline>> response = airlineController.getAllAirlines();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockAirlines, response.getBody());
        verify(airlineService, times(1)).getAllAirlines();
    }

    @Test
    void testSaveAirline() {
        Airline mockAirline = new Airline();

        ResponseEntity<String> response = airlineController.saveAirline(mockAirline);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Airline added successfully", response.getBody());
        verify(airlineService, times(1)).saveAirline(mockAirline);
    }

    @Test
    void testDeleteAirlineById() {
        Long airlineId = 1L;
        doNothing().when(airlineService).deleteAirlineById(airlineId);

        ResponseEntity<String> response = airlineController.deleteAirlineById(airlineId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Airline with ID " + airlineId + " deleted successfully", response.getBody());
        verify(airlineService, times(1)).deleteAirlineById(airlineId);
    }
}

