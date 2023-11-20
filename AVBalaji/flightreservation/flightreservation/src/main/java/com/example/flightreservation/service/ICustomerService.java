package com.example.flightreservation.service;

import com.example.flightreservation.entity.Customer;

public interface ICustomerService {
    boolean registerCustomer(Customer customer);

    boolean performLogin(String email, String password);

    boolean saveCustomer(Customer customer);
}
