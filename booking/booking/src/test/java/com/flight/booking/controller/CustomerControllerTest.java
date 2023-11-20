package com.flight.booking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.flight.booking.requestDTO.CustomerDTO;
import com.flight.booking.requestDTO.LoginDTO;
import com.flight.booking.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testCustomerLogin() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO("customer1@gmail.com", "password123");
        when(customerService.doLogin("customer1@gmail.com", "password123")).thenReturn(true);

        // Act
        ResponseEntity<Boolean> responseEntity = customerController.customerLogin(loginDTO);

        // Assert
        verify(customerService, times(1)).doLogin("customer1@gmail.com", "password123");
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertTrue(responseEntity.getBody())
        );
    }

    @Test
    void testCustomerRegistration() {

        Date date = Date.valueOf("1999-09-14");
        CustomerDTO customerDTO = new CustomerDTO("customer1",date,"customer1@gmail.com","password123","customer1SSN","SSN0001");

        when(customerService.registerCustomer(customerDTO)).thenReturn("Customer Saved Successfully");

        // Act
        ResponseEntity<String> responseEntity = customerController.customerRegistration(customerDTO);

        // Assert
        verify(customerService, times(1)).registerCustomer(customerDTO);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertEquals("Customer Saved Successfully", responseEntity.getBody())
        );
    }
}