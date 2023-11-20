package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    }

