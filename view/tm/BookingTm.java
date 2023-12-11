package view.tm;

import javafx.scene.control.Button;

import java.util.Date;

public class BookingTm {
    private String BookingId;
    private Date date;
    private String customer;
    private double totalCost;
    private Button btn;


    public BookingTm() {

    }

    public BookingTm(String bookingId, Date date, String customer, double totalCost, Button btn) {
        BookingId = bookingId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
