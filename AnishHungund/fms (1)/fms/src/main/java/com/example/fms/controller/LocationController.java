package com.example.fms.controller;

import com.example.fms.entity.LocationMaster;
import com.example.fms.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/locationApi")
public class LocationController {

        @Autowired
        private ILocationService locationService;
        @GetMapping("/all")
        public ResponseEntity<List<LocationMaster>> getAllLocations() {
            List<LocationMaster> locations = locationService.getAllLocations();
            if (locations.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(locations);
            }
        }
    }

