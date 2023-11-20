package com.example.fms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight_master")
public class FlightMaster {
    @Id
    @Column(name = "f_no")
    private Long flightNumber;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private AirlineMaster airline;

    @Column(name = "tot_seats")
    private int totalSeats;
    @ManyToOne
    @JoinColumn(name = "src", referencedColumnName = "location_id")
    private LocationMaster source;

    @ManyToOne
    @JoinColumn(name = "dest", referencedColumnName = "location_id")
    private LocationMaster destination;

    @Column(name = "depart_time")
    private String departureTime;

    @Column(name = "fare")
    private int fare;

    @Column(name = "ava_seats")
    private int availableSeats;

    @Column(name = "depart_date")
    private Date departDate;
}

