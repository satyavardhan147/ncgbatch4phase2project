package com.altimetrik.ars.repository;

import com.altimetrik.ars.model.Flight;
import com.altimetrik.ars.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findBySourceAndDestination(Location source, Location destination);

}
