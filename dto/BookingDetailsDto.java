package dto;

public class BookingDetailsDto {
    private String RoomId;
    private String bookingId;
    private String availability;
    private double Price;
    private int dayCount;


    public BookingDetailsDto(String roomId) {

        RoomId = roomId;
    }

    public BookingDetailsDto() {
    }

    public BookingDetailsDto(String bookingId, double price, int dayCount) {
        this.bookingId = bookingId;
        this. Price = price;
        this.dayCount = dayCount;
    }

    public BookingDetailsDto(String roomId, String bookingId, String availability, double price, int dayCount) {
        this.RoomId = roomId;
        this.bookingId = bookingId;
        this.availability = availability;
        this. Price = price;
        this.dayCount = dayCount;
    }

    public BookingDetailsDto(String roomId, String availability, double price, int dayCount) {
        this.RoomId = roomId;
        this.availability = availability;
        this. Price = price;
        this.dayCount = dayCount;
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

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
