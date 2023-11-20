package com.example.fms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking_info")
public class BookingMaster {
    @Id
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "booking_date")
    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "f_no")
    private FlightMaster flight;

    @ManyToOne
    @JoinColumn(name = "c_id")
    private CustomerMaster customer;

    @Column(name = "price")
    private int totalPrice;

    @Column(name = "seats_book")
    private int seatsBooked;

    @Column(name = "depart_date")
    private Date departureDate;

    public void setCustomerId(long l) {
    }
}
