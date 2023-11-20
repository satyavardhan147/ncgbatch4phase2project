package com.example.fms.repository;

import com.example.fms.entity.CustomerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ICustomerRepository extends JpaRepository<CustomerMaster, Long> {
    Optional<CustomerMaster> findByEmail(String email);

}
