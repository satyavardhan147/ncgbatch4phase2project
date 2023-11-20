package com.altimetrik.ars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(generator = "bookingId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bookingId", initialValue = 20201, sequenceName = "bookingId")
    private Long bookingId;

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    @Pattern(regexp = "[A-Z0-9]{3,10}", message = "Invalid flight number format")
    private String flightNumber;

    @Positive(message = "Number of seats booked must be positive")
    private int seatsBooked;

    @Positive
    private int price;

    @NotNull(message = "Departure date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "customer")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "flight")
    @JsonIgnore
    private Flight flight;

    private Long flightId;

    private Long customerId;

    @PrePersist
    public void prePersist() {
        if (bookingDate == null) {
            bookingDate = new Date();
        }
        if (customerId == 0) {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerId(customerId);
            this.customer = newCustomer;
        }
    }
}
