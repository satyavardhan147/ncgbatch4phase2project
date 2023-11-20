package com.flight.booking.service;


import com.flight.booking.entity.Customer;
import com.flight.booking.repository.CustomerRepository;
import com.flight.booking.requestDTO.CustomerDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CustomerServiceImpTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;
    AutoCloseable autoCloseable;
    Customer customer;
    CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImp(customerRepository);
        Date date = Date.valueOf("1999-09-14");
        customerDTO = new CustomerDTO("customer1",date,"customer1@gmail.com","password123","customer1SSN","SSN0001");
        customer = new Customer("customer1",date,"customer1@gmail.com","password123","customer1SSN","SSN0001");

    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void doLogin() {
        mock(Customer.class);
        mock(CustomerRepository.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertThat(customerService.doLogin("customer1@gmail.com","password123")).isEqualTo("Login Succesfull");

    }

    @Test
    void registerCustomer() {
       mock(Customer.class);
        mock(CustomerRepository.class);
        when(customerRepository.save(customer)).thenReturn(customer);
        Assertions.assertThat(customerService.registerCustomer(customerDTO)).isEqualTo("Customer Saved Successfully");

    }
}