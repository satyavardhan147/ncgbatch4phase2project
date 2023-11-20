package com.example.fms.service;

import com.example.fms.entity.AirlineMaster;
import com.example.fms.repository.IAirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService implements IAirlineService {
    @Autowired
    private IAirlineRepository airlineRepository;

    public List<AirlineMaster> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public List<AirlineMaster> getAllAirlines(Long airlineId) {
        return null;
    }
}



