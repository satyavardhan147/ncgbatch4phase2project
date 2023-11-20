package com.altimetrik.ars.repository;

import com.altimetrik.ars.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline findByName(String airlineName);
}
