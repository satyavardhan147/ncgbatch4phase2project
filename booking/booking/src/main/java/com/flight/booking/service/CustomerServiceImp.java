package com.flight.booking.service;

import com.flight.booking.entity.Customer;
import com.flight.booking.repository.CustomerRepository;
import com.flight.booking.requestDTO.CustomerDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService{


    private CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean doLogin(String email , String password)
    {
       Optional<Customer> customer = customerRepository.findByEmailAndPassword(email,password);
       return customer.isPresent();

    }

    public String registerCustomer(CustomerDTO customerDTO) {

        String customerName = customerDTO.getCustomerName();

        Date dateOfBirth = customerDTO.getDateOfBirth();

        String email = customerDTO.getEmail();

        String password = customerDTO.getPassword();
        String ssnType = customerDTO.getSsnType();

        String ssnNumber = customerDTO.getSsnNumber();


        Customer customer = new Customer(customerName,dateOfBirth,email,password,ssnType,ssnNumber);
        customerRepository.save(customer);
        return "Customer Saved Successfully";
    }
}
