package com.altimetrik.ars.repository;

import com.altimetrik.ars.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByLocationName(String locationName);
}
