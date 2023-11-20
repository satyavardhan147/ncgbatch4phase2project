package com.example.fms.repository;

import com.example.fms.entity.FlightMaster;
import com.example.fms.entity.LocationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
@Repository
public interface IFlightRepository extends JpaRepository <FlightMaster, Long> {
    List<FlightMaster> findBySourceAndDestinationAndDepartDate (LocationMaster source, LocationMaster destination, Date date);
}
