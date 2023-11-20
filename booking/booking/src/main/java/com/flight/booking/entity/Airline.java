package com.flight.booking.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Airline {

    @Id
    @GeneratedValue(generator = "trainsequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trainsequence" , allocationSize = 100,initialValue = 10000,sequenceName = "trainsequence")
    private int id;

    private String name;

    private Date dateOfOperation;

    @OneToMany(mappedBy = "airline" ,cascade = CascadeType.ALL)
    private List<Flight> flightList = new ArrayList<>();

    public Airline() {
    }

    public Airline(String name, Date dateOfOperation) {
        this.name = name;
        this.dateOfOperation = dateOfOperation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfOperation() {
        return dateOfOperation;
    }

    public void setDateOfOperation(Date dateOfOperation) {
        this.dateOfOperation = dateOfOperation;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(Flight flight) {
        this.flightList.add(flight);
    }
}
