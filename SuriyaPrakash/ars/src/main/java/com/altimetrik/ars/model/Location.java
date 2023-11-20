package com.altimetrik.ars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(generator = "locationId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "locationId", initialValue = 50101, sequenceName = "locationId")
    private Long locationId;

    @NotBlank(message = "Location name cannot be blank")
    private String locationName;

}
