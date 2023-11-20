package com.altimetrik.ars.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Airline {

    @Id
    @GeneratedValue(generator = "airlineId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "airlineId", initialValue = 40101, sequenceName = "airlineId")
    private Long airlineId;

    @NotBlank(message = "Airline name cannot be blank")
    private String name;

}
