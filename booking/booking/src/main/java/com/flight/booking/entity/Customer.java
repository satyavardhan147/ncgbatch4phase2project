package com.flight.booking.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "trainsequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trainsequence" , allocationSize = 100,initialValue = 10000,sequenceName = "trainsequence")
    private int customerId;

    private String customerName;

    private Date dateOfBirth;


    private String email;


    private String password;
    @Column(name = "ssn_type")
    private String ssnType;

    @Column(name = "ssn_number")
    private String ssnNumber;


    @OneToMany(mappedBy = "customer" , cascade = CascadeType.ALL)
    private List<Booking> bookingList = new ArrayList<>();
    public Customer() {
    }

    public Customer(String customerName, Date dateOfBirth, String email, String password, String ssnType, String ssnNumber) {

        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.ssnType = ssnType;
        this.ssnNumber = ssnNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsnType() {
        return ssnType;
    }

    public void setSsnType(String ssnType) {
        this.ssnType = ssnType;
    }

    public String getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(Booking booking) {
        this.bookingList.add(booking);
    }
}
