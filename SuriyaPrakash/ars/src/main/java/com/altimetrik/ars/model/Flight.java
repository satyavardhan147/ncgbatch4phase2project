package com.altimetrik.ars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(generator = "flightId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "flightId", initialValue = 30101, sequenceName = "flightId")
    private Long flightId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id")
    @JsonIgnore
    private Airline airline;

    @NotNull(message = "Total seats must be specified")
    private int totalSeats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_id")
    @JsonIgnore
    private Location source;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_id")
    @JsonIgnore
    private Location destination;

    @NotBlank(message = "Departure time cannot be blank")
    private String departureTime;

    @NotNull(message = "Fare must be specified")
    private double fare;

    @NotNull(message = "Available seats must be specified")
    private int availableSeats;

    @NotNull(message = "Departure date cannot be null")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    private String airlineName;

    private String sourceLocationName;

    private String destinationLocationName;

    @PostLoad
    public void populateTransientFields() {
        if (airline != null) {
            airlineName = airline.getName();
        }
        if (source != null) {
            sourceLocationName = source.getLocationName();
        }
        if (destination != null) {
            destinationLocationName = destination.getLocationName();
        }
    }
}
