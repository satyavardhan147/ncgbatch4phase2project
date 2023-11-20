package com.example.flightreservation.repository;
import com.example.flightreservation.entity.CancelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CancelTicketRepository extends JpaRepository<CancelTicket, Long> {
}
