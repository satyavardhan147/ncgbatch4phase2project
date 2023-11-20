package com.example.fms.repository;

import com.example.fms.entity.BookingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBookingRepository extends JpaRepository<BookingMaster, Long> {
    List<BookingMaster> findByCustomerId(Long customerId);
}
