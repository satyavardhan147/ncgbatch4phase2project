package com.example.fms.implementation;

import com.example.fms.entity.CustomerMaster;
import com.example.fms.repository.ICustomerRepository;
import com.example.fms.repository.ILoginRepository;
import com.example.fms.service.CustomerMasterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomerImplementationTest {

    @Mock
    private ICustomerRepository customerRepository;

    @Mock
    private ILoginRepository loginRepository;

    @InjectMocks
    private CustomerMasterService customerService;

    @Test
    void testRegisterCustomer() {
        Mockito.when(customerRepository.save(Mockito.any(CustomerMaster.class))).thenReturn(new CustomerMaster());

        boolean result = customerService.registerCustomer(new CustomerMaster());
        assertTrue(result);
    }

    @Test
    void testPerformLogin() {
        String email = "test@example.com";
        String password = "password";
        CustomerMaster customer = new CustomerMaster();
        customer.setEmail(email);
        customer.setPassword(password);

        // Mock the behavior of customerRepository.findByEmail to return an Optional containing the customer
        Mockito.when(customerRepository.findByEmail(email)).thenReturn(Optional.of(customer));

        boolean result = customerService.performLogin(email, password);
        assertTrue(result);
    }

    @Test
    void testSaveCustomer_Success() {
        // Mock the behavior of loginRepository.save to return a CustomerMaster
        Mockito.when(loginRepository.save(Mockito.any(CustomerMaster.class))).thenReturn(new CustomerMaster());

        boolean result = customerService.saveCustomer(new CustomerMaster());
        assertTrue(result);
    }

    @Test
    void testSaveCustomer_Failure() {
        // Mock the behavior of loginRepository.save to throw a RuntimeException
        Mockito.when(loginRepository.save(Mockito.any(CustomerMaster.class))).thenThrow(RuntimeException.class);

        boolean result = customerService.saveCustomer(new CustomerMaster());
        assertFalse(result);
    }
}
