package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Customer;
import com.altimetrik.ars.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> mockCustomers = Arrays.asList(new Customer(), new Customer());
        when(customerService.getAllCustomers()).thenReturn(mockCustomers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockCustomers, response.getBody());
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testCustomerRegistration() {
        Customer mockCustomer = new Customer();
        when(customerService.saveCustomerDetails(any())).thenReturn(mockCustomer);

        ResponseEntity<Customer> response = customerController.customerRegistration(new Customer());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockCustomer, response.getBody());
        verify(customerService, times(1)).saveCustomerDetails(any());
    }

    @Test
    void testLogin() {
        String email = "test@example.com";
        String password = "password";
        String expectedResult = "Login successful";
        when(customerService.login(email, password)).thenReturn(expectedResult);

        ResponseEntity<String> response = customerController.login(email, password);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResult, response.getBody());
        verify(customerService, times(1)).login(email, password);
    }

    @Test
    void testUpdateCustomerDetails() {
        Long customerId = 1L;
        Customer mockUpdatedCustomer = new Customer();
        when(customerService.updateCustomerDetails(eq(customerId), any())).thenReturn(mockUpdatedCustomer);

        ResponseEntity<Customer> response = customerController.updateCustomerDetails(customerId, new Customer());

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUpdatedCustomer, response.getBody());
        verify(customerService, times(1)).updateCustomerDetails(eq(customerId), any());
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L;
        doNothing().when(customerService).deleteCustomer(customerId);

        ResponseEntity<String> response = customerController.deleteCustomer(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer with ID " + customerId + " deleted successfully", response.getBody());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}

