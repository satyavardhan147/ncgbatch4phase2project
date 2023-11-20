package com.flight.booking.controller;


import com.flight.booking.exception.EmptyInputException;
import com.flight.booking.requestDTO.CustomerDTO;
import com.flight.booking.requestDTO.LoginDTO;
import com.flight.booking.service.CustomerService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-api")
public class CustomerController
{
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/customer-login")
    public ResponseEntity<Boolean> customerLogin(@Valid @RequestBody LoginDTO loginDTO)
    {
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        boolean value = customerService.doLogin(email,password);
        if(value) {
            logger.info("login Succesfull");
            return new ResponseEntity<>(value, HttpStatus.OK);
        }

        logger.error("username or password entered is wrong");
        return new ResponseEntity<>(value,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register-customer")
    public ResponseEntity<String> customerRegistration(@Valid @RequestBody CustomerDTO customerDTO)
    {
        if(customerDTO == null) {

            logger.error("Input filed is Empty");
            throw new EmptyInputException("Input Field is Empty , Please Enter Valid Input");

        }
        String s =  customerService.registerCustomer(customerDTO);
        logger.info("Customer registration is successfull");
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
}
