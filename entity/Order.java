package entity;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String OrderId;
    private Date date;
    private String customer;
    private double totalCost;
    private ArrayList<ItemDetails> itemDetails;


    public Order() {
    }


    public Order(String orderId, Date date, String customer, double totalCost) {
        OrderId = orderId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
    }

    public Order(String orderId, Date date, String customer, double totalCost, ArrayList<ItemDetails> itemDetails) {
        OrderId = orderId;
        this.date = date;
        this.customer = customer;
        this.totalCost = totalCost;
        this.itemDetails = itemDetails;
    }



    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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

    public ArrayList<ItemDetails> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ArrayList<ItemDetails> itemDetails) {
        this.itemDetails = itemDetails;
    }
}
