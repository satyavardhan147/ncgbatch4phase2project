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
@Table(name = "airline_master")
public class AirlineMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "airlinesequence",allocationSize = 100, initialValue =50000, sequenceName = "airlinesequence")
    @Column(name = "a_id")
    private Long id;
    @Column(name = "a_name")
    private String airlineName;
    @Column(name = "dop")
    private Date dateOfOperation;
}