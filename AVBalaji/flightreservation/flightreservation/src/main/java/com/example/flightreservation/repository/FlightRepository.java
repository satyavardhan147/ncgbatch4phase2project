package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Integer> {
    List<Flight> findBySourceAndDestinationAndDepartureDate(String source, String destination, Date departureDate);
}
