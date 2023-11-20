package com.example.flightreservation.repository;

import com.example.flightreservation.entity.Customer;
import com.example.flightreservation.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Customer, Long> {
    Optional<Login> findByEmail(String email);
}