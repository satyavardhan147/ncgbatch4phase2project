package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Location;
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

class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLocationById() {
        Long locationId = 1L;
        Location mockLocation = new Location();
        when(locationService.getLocationById(locationId)).thenReturn(mockLocation);

        ResponseEntity<Location> response = locationController.getLocationById(locationId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockLocation, response.getBody());
        verify(locationService, times(1)).getLocationById(locationId);
    }

    @Test
    void testGetLocationByName() {
        String locationName = "Test Location";
        Location mockLocation = new Location();
        when(locationService.getLocationByName(locationName)).thenReturn(mockLocation);

        ResponseEntity<Location> response = locationController.getLocationByName(locationName);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockLocation, response.getBody());
        verify(locationService, times(1)).getLocationByName(locationName);
    }

    @Test
    void testGetAllLocations() {
        List<Location> mockLocations = Arrays.asList(new Location(), new Location());
        when(locationService.getAllLocations()).thenReturn(mockLocations);

        ResponseEntity<List<Location>> response = locationController.getAllLocations();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockLocations, response.getBody());
        verify(locationService, times(1)).getAllLocations();
    }

    @Test
    void testSaveLocation() {
        Location mockLocation = new Location();
        when(locationService.saveLocation(any())).thenReturn(mockLocation);

        ResponseEntity<Location> response = locationController.saveLocation(new Location());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockLocation, response.getBody());
        verify(locationService, times(1)).saveLocation(any());
    }
}
