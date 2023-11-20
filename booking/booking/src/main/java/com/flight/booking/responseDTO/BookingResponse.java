package com.flight.booking.responseDTO;



import java.sql.Date;

public class BookingResponse {

    private Date bookingDate;


    private int flightId;

    private int customerId;

    private int price;

    private int seatsBook;

    private Date departureDate;

    public BookingResponse() {
    }

    public BookingResponse(Date bookingDate, int flightId, int customerId, int price, int seatsBook, Date departureDate) {
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
