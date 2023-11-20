package com.example.fms.repository;

import com.example.fms.entity.AirlineMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAirlineRepository extends JpaRepository<AirlineMaster, Long> {
    List<AirlineMaster> getAllAirlines(Long a_id);
}
