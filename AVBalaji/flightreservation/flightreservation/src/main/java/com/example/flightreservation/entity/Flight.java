package com.example.flightreservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(generator = "flightSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "flightSequence" , allocationSize = 100,initialValue = 10000,sequenceName = "flightSequence")
    private int id;

    private int totalSeats;

    private String source;

    private String destination;

    private String departureTime;

    private int fare;

    private int availableSeats;

    @NotNull
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private Airline airline;

    public Flight(int totalSeats, String source, String destination, String departureTime, int fare, int availableSeats, Date departureDate, Airline airline) {
        this.totalSeats = totalSeats;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.fare = fare;
        this.availableSeats = availableSeats;
        this.departureDate = departureDate;
        this.airline = airline;
    }
}