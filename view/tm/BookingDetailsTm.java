package view.tm;

public class BookingDetailsTm {
    private String RoomId;
    private String availability;
    private double Price;
    private int dayCount;
    private double total;

    public BookingDetailsTm() {
    }

    public BookingDetailsTm(String roomId, String availability, double price, int dayCount, double total) {
        RoomId = roomId;
        this.availability = availability;
        Price = price;
        this.dayCount = dayCount;
        this.total = total;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
