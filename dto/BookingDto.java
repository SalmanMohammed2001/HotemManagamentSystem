package dto;





import entity.BookingDetails;

import java.util.ArrayList;
import java.util.Date;

public class BookingDto {
    private String BookingId;
    private Date date;
    private String customer;
    private double totalCost;
    private ArrayList<BookingDetailsDto> BookingList;


    public BookingDto() {
    }

    public BookingDto(String bookingId, Date date, String customer, double totalCost) {
        BookingId = bookingId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
    }

    public BookingDto(String bookingId, Date date, String customer, double totalCost, ArrayList<BookingDetailsDto> bookingList) {
        BookingId = bookingId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
        BookingList = bookingList;
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

    public ArrayList<BookingDetailsDto> getBookingList() {
        return BookingList;
    }

    public void setBookingList(ArrayList<BookingDetailsDto> bookingList) {
        BookingList = bookingList;
    }
}
