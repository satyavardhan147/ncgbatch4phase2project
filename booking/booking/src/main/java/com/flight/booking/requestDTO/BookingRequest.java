package com.flight.booking.requestDTO;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


import java.sql.Date;

public class BookingRequest
{
    @NotEmpty(message = "Booking Date field is Empty")
    private Date bookingDate;

    @NotEmpty(message = "flight Id field is empty")
    @Min(value = 1,message = "flightId field is Negative")
    private int flightId;

    @NotEmpty(message = "customer Id is Empty")
    private int customerId;

    @NotEmpty(message = "price field is empty")
    private int price;

    @NotEmpty(message = "seatsBook field is Empty")
    private int seatsBook;
    @NotEmpty(message = "departureDate field is Empty")
    private Date departureDate;

    public BookingRequest() {
    }

    public BookingRequest(Date bookingDate, int flightId, int customerId, int price, int seatsBook, Date departureDate) {
        this.bookingDate = bookingDate;
        this.flightId = flightId;
        this.customerId = customerId;
        this.price = price;
        this.seatsBook = seatsBook;
        this.departureDate = departureDate;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
