package com.example.fms.repository;

import com.example.fms.entity.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ILocationRepository extends JpaRepository<LocationMaster, Long> {
    List<LocationMaster> getAllLocations (LocationMaster source, LocationMaster destination, Date date);
}
