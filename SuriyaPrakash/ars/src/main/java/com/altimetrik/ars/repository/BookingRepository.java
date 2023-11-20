package com.altimetrik.ars.repository;

import com.altimetrik.ars.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
