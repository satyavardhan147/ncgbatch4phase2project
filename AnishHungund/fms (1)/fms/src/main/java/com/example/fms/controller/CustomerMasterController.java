package com.example.fms.controller;

import com.example.fms.entity.CustomerMaster;
import com.example.fms.service.ICustomerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerApi")
public class CustomerMasterController {
    @Autowired
    private ICustomerMasterService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerMaster customer) {
        boolean isRegistered = customerService.registerCustomer(customer);
        if (isRegistered) {
            return ResponseEntity.ok("Customer registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }
}
