package com.flight.booking.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Booking
{
    @Id
    @GeneratedValue(generator = "trainsequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "trainsequence" , allocationSize = 100,initialValue = 10000,sequenceName = "trainsequence")
    private int id;

    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "cusomer_id")
    private Customer customer;

    private int price;

    private int seatsBook;

    private Date departureDate;

    public Booking() {
    }

    public Booking(Date bookingDate, Flight flight, Customer customer, int price, int seatsBook, Date departureDate) {
        this.bookingDate = bookingDate;
        this.flight = flight;
        this.customer = customer;
        this.price = price;
        this.seatsBook = seatsBook;
        this.departureDate = departureDate;
    }

    public int getId() {
        return id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeatsBook() {
        return seatsBook;
    }

    public void setSeatsBook(int seatsBook) {
        this.seatsBook = seatsBook;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


}
