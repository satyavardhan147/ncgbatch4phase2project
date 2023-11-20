package com.altimetrik.ars.service;

import com.altimetrik.ars.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer saveCustomerDetails(Customer customer);
    String login(String email, String password);
    Customer updateCustomerDetails(Long customerId, Customer updatedCustomer);
    void deleteCustomer(Long customerId);
}
