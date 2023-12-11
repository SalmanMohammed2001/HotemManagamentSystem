package dto;

public class CustomDto {
    private String OrderId;
    private String id;
    private double unitePrice;
    private int qty;

    //===========
    private String bookingId;
    private String RoomId;
    private double Price;
    private String availability;
    private int dayCount;





    public CustomDto() {
    }

    public CustomDto(String bookingId, String roomId, double price, String availability, int dayCount) {
        this.bookingId=bookingId;
        this.RoomId=roomId;
        this.Price=price;
        this.availability=availability;
        this.dayCount=dayCount;
    }

    public CustomDto(String roomId, double price, String availability, int dayCount) {
        this.RoomId=roomId;
        this.Price=price;
        this.availability=availability;
        this.dayCount=dayCount;

    }

    //==============

    public CustomDto(String orderId, String id, double unitePrice, int qty) {
        this. OrderId = orderId;
        this.id = id;
        this.unitePrice = unitePrice;
        this.qty = qty;
    }
    public CustomDto(String id, double unitePrice, int qty) {
        this.id = id;
        this.unitePrice = unitePrice;
        this.qty = qty;
    }





    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(double unitePrice) {
        this.unitePrice = unitePrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
}
