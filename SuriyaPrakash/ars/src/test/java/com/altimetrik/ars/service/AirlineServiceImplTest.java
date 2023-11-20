package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.AirlineException;
import com.altimetrik.ars.model.Airline;
import com.altimetrik.ars.repository.AirlineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AirlineServiceImplTest {

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineServiceImpl airlineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAirlineById() {
        Long airlineId = 1L;
        Airline expectedAirline = new Airline();
        when(airlineRepository.findById(airlineId)).thenReturn(Optional.of(expectedAirline));

        Airline result = airlineService.getAirlineById(airlineId);
        assertNotNull(result);
        assertEquals(expectedAirline, result);
    }

    @Test
    void testGetAirlineByIdNotFound() {
        Long airlineId = 1L;
        when(airlineRepository.findById(airlineId)).thenReturn(Optional.empty());
        assertThrows(AirlineException.class, () -> airlineService.getAirlineById(airlineId));
    }

    @Test
    void testGetAirlineByName() {
        String airlineName = "Test Airline";
        Airline expectedAirline = new Airline();
        when(airlineRepository.findByName(airlineName)).thenReturn(expectedAirline);

        Airline result = airlineService.getAirlineByName(airlineName);
        assertNotNull(result);
        assertEquals(expectedAirline, result);
    }

    @Test
    void testGetAirlineByNameNotFound() {
        String airlineName = "Nonexistent Airline";
        when(airlineRepository.findByName(airlineName)).thenReturn(null);
        assertThrows(AirlineException.class, () -> airlineService.getAirlineByName(airlineName));
    }

    @Test
    void testSaveAirline() {
        Airline airlineToSave = new Airline();
        when(airlineRepository.findByName(airlineToSave.getName())).thenReturn(null);
        when(airlineRepository.save(airlineToSave)).thenReturn(airlineToSave);

        Airline result = airlineService.saveAirline(airlineToSave);
        assertNotNull(result);
        assertEquals(airlineToSave, result);
    }

    @Test
    void testSaveAirlineDuplicateName() {
        Airline airlineToSave = new Airline();
        when(airlineRepository.findByName(airlineToSave.getName())).thenReturn(new Airline());
        assertThrows(DataIntegrityViolationException.class, () -> airlineService.saveAirline(airlineToSave));
    }

    @Test
    void testGetAllAirlines() {
        List<Airline> expectedAirlines = Arrays.asList(new Airline(), new Airline());
        when(airlineRepository.findAll()).thenReturn(expectedAirlines);

        List<Airline> result = airlineService.getAllAirlines();
        assertNotNull(result);
        assertEquals(expectedAirlines.size(), result.size());
    }

    @Test
    void testDeleteAirlineById() {
        Long airlineId = 1L;
        when(airlineRepository.existsById(airlineId)).thenReturn(true);
        airlineService.deleteAirlineById(airlineId);
        verify(airlineRepository, times(1)).deleteById(airlineId);
    }

    @Test
    void testDeleteAirlineByIdNotFound() {
        Long airlineId = 1L;
        when(airlineRepository.existsById(airlineId)).thenReturn(false);
        assertThrows(AirlineException.class, () -> airlineService.deleteAirlineById(airlineId));
    }
}

