package com.example.flightreservation.service;

import com.example.flightreservation.entity.Booking;
import com.example.flightreservation.entity.CancelTicket;

public interface ICancelTicketService {
    CancelTicket cancelBooking(Booking booking);
}
