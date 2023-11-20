package com.example.flightreservation.service;
import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.CancelTicket;
import com.example.flightreservation.repository.CancelTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelTicketService implements ICancelTicketService {

    private final CancelTicketRepository cancelTicketRepository;

    @Autowired
    public CancelTicketService(CancelTicketRepository cancelTicketRepository) {
        this.cancelTicketRepository = cancelTicketRepository;
    }
        public CancelTicket cancelBooking(Booking booking) {
            CancelTicket cancelTicket = new CancelTicket(booking);
            return cancelTicketRepository.save(cancelTicket);
        }
    }
