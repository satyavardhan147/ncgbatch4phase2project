package com.altimetrik.ars.repository;

import com.altimetrik.ars.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmailAndPassword(String email, String password);
}
