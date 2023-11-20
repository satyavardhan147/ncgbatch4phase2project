package com.altimetrik.ars.service;

import com.altimetrik.ars.exception.CustomerException;
import com.altimetrik.ars.model.Customer;
import com.altimetrik.ars.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
            logger.debug("Getting all customers");
            return customerRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public Customer saveCustomerDetails(Customer customer) {
            logger.debug("Saving customer details: {}", customer);
            return customerRepository.save(customer);
    }

    @Override
    public String login(String email, String password) {
            logger.debug("Logging in with email: {}", email);
            Customer customer = customerRepository.findByEmailAndPassword(email, password);
            if (customer != null) {
                logger.info("Login successful for customer: {}", customer.getName());
                return "Login successful. Welcome, " + customer.getName() + "!";
            } else {
                logger.warn("Invalid email or password. Login failed for email: {}", email);
                return "Invalid email or password. Login failed.";
            }
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public Customer updateCustomerDetails(Long customerId, Customer updatedCustomer) {
            logger.debug("Updating customer details with ID: {}", customerId);
            Customer existingCustomer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));

            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPassword(updatedCustomer.getPassword());

            return customerRepository.save(existingCustomer);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    @Override
    public void deleteCustomer(Long customerId) {
            logger.debug("Deleting customer with ID: {}", customerId);

            Customer existingCustomer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomerException("Customer not found with ID: " + customerId));

            customerRepository.delete(existingCustomer);
    }

}
