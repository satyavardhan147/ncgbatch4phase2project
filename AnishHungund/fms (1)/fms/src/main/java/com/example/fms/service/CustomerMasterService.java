package com.example.fms.service;

import com.example.fms.entity.CustomerMaster;
import com.example.fms.repository.ICustomerRepository;
import com.example.fms.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerMasterService implements ICustomerMasterService {
    private final ICustomerRepository customerRepository;
    private final ILoginRepository loginRepository;

    @Autowired
    public CustomerMasterService(ICustomerRepository customerRepository, ILoginRepository loginRepository) {
        this.customerRepository = customerRepository;
        this.loginRepository = loginRepository;
    }

    public boolean registerCustomer(CustomerMaster customer) {
        customerRepository.save(customer);
        return true;
    }

    public boolean performLogin(String email, String password) {
        Optional<CustomerMaster> customer = customerRepository.findByEmail(email);
        return customer.map(l -> l.getPassword().equals(password)).orElse(false);
    }

    @Override
    public boolean saveCustomer(CustomerMaster customer) {
        try {
            loginRepository.save(customer);
            return true;
        } catch (Exception e) {
            // Log the exception or handle it as needed
            throw new RuntimeException("Failed to save customer", e);
        }
    }
}

