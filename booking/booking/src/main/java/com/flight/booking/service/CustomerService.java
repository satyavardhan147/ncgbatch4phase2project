package com.flight.booking.service;


import com.flight.booking.requestDTO.CustomerDTO;

public interface CustomerService
{
    public boolean doLogin(String email , String password);
    public String registerCustomer(CustomerDTO customerDTO);
}
