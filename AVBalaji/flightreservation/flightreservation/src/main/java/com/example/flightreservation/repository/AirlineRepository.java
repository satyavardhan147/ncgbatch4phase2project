package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline,Integer>
{
    Optional<Airline> findByName(String name);
}
