package com.flight.booking.repository;


import com.flight.booking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
    public Optional<Customer> findByEmailAndPassword(String username,String password);
}
