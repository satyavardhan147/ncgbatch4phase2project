package com.example.fms.repository;

import com.example.fms.entity.CustomerMaster;
import com.example.fms.entity.LoginMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ILoginRepository extends JpaRepository<CustomerMaster, Long> {
    Optional<LoginMaster> findByEmail(String email);
}
