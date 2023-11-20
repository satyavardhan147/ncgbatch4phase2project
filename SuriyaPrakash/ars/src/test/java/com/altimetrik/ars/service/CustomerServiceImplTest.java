package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.CustomerException;
import com.altimetrik.ars.model.Customer;
import com.altimetrik.ars.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> expectedCustomers = Arrays.asList(new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        List<Customer> result = customerService.getAllCustomers();
        assertNotNull(result);
        assertEquals(expectedCustomers.size(), result.size());
    }

    @Test
    void testSaveCustomerDetails() {
        Customer customerToSave = new Customer();
        when(customerRepository.save(customerToSave)).thenReturn(customerToSave);

        Customer result = customerService.saveCustomerDetails(customerToSave);
        assertNotNull(result);
        assertEquals(customerToSave, result);
    }

    @Test
    void testLoginSuccessful() {
        String email = "test@example.com";
        String password = "password";

        Customer expectedCustomer = new Customer();
        expectedCustomer.setName("Test Customer");
        when(customerRepository.findByEmailAndPassword(email, password)).thenReturn(expectedCustomer);

        String result = customerService.login(email, password);
        assertEquals("Login successful. Welcome, " + expectedCustomer.getName() + "!", result);
    }

    @Test
    void testLoginInvalidCredentials() {
        String email = "test@example.com";
        String password = "invalidPassword";
        when(customerRepository.findByEmailAndPassword(email, password)).thenReturn(null);

        String result = customerService.login(email, password);
        assertEquals("Invalid email or password. Login failed.", result);
    }

    @Test
    void testUpdateCustomerDetails() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Updated Customer");

        Customer existingCustomer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(existingCustomer);

        Customer result = customerService.updateCustomerDetails(customerId, updatedCustomer);
        assertNotNull(result);
        assertEquals(updatedCustomer.getName(), result.getName());
    }

    @Test
    void testUpdateCustomerDetailsNotFound() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> customerService.updateCustomerDetails(customerId, updatedCustomer));
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L;
        Customer existingCustomer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));
        verify(customerRepository, times(1)).delete(existingCustomer);
    }

    @Test
    void testDeleteCustomerNotFound() {
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> customerService.deleteCustomer(customerId));
    }
}


