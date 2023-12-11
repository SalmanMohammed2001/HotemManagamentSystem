package entity;

;

import java.util.ArrayList;
import java.util.Date;

public class Booking {
    private String BookingId;
    private Date date;
    private String customer;
    private double totalCost;
    private ArrayList<BookingDetails> BookingList;


    public Booking() {
    }

    public Booking(String bookingId, Date date, String customer, double totalCost) {
        BookingId = bookingId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
    }

    public Booking(String bookingId, Date date, String customer, double totalCost, ArrayList<BookingDetails> bookingList) {
        this.BookingId = bookingId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
       this. BookingList = bookingList;
    }



    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        BookingId = bookingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<BookingDetails> getBookingList() {
        return BookingList;
    }

    public void setBookingList(ArrayList<BookingDetails> bookingList) {
        BookingList = bookingList;
    }
}
