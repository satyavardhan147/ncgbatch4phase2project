package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.LocationException;
import com.altimetrik.ars.model.Location;
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

class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLocationById() {
        Long locationId = 1L;
        Location expectedLocation = new Location();
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(expectedLocation));
        Location result = locationService.getLocationById(locationId);
        assertNotNull(result);
        assertEquals(expectedLocation, result);
    }

    @Test
    void testGetLocationByIdNotFound() {
        Long locationId = 1L;
        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());
        assertThrows(LocationException.class, () -> locationService.getLocationById(locationId));
    }

    @Test
    void testGetLocationByName() {
        String locationName = "TestLocation";
        Location expectedLocation = new Location();
        when(locationRepository.findByLocationName(locationName)).thenReturn(expectedLocation);
        Location result = locationService.getLocationByName(locationName);
        assertNotNull(result);
        assertEquals(expectedLocation, result);
    }

    @Test
    void testGetLocationByNameNotFound() {
        String locationName = "NonExistentLocation";
        when(locationRepository.findByLocationName(locationName)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> locationService.getLocationByName(locationName));
    }

    @Test
    void testSaveLocation() {
        Location locationToSave = new Location();
        when(locationRepository.findByLocationName(anyString())).thenReturn(null);
        when(locationRepository.save(any())).thenReturn(locationToSave);
        Location result = locationService.saveLocation(locationToSave);
        assertNotNull(result);
        assertEquals(locationToSave, result);
    }


    @Test
    void testGetAllLocations() {
        List<Location> expectedLocations = Arrays.asList(new Location(), new Location());
        when(locationRepository.findAll()).thenReturn(expectedLocations);
        List<Location> result = locationService.getAllLocations();
        assertNotNull(result);
        assertEquals(expectedLocations.size(), result.size());
    }
}
