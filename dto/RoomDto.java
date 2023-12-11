package dto;

public class RoomDto {

    private String id;
    private String roomType;
    private String available;
    private String cleaned;
    private double price;


    public RoomDto() {
    }

    public RoomDto(String roomType, String available, String cleaned , double price) {
        this.roomType = roomType;
        this.available = available;
        this.cleaned = cleaned;
        this.price = price;
    }

    public RoomDto(String roomType, String available, String cleaned , double price,String id) {
        this.roomType = roomType;
        this.available = available;
        this.cleaned = cleaned;
        this.price = price;
        this.id=id;
    }

    public RoomDto(String id, String roomType, String available, String cleaned, double price) {
        this.id = id;
        this.roomType = roomType;
        this.available = available;
        this.cleaned = cleaned;
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getCleaned() {
        return cleaned;
    }

    public void setCleaned(String cleaned) {
        this.cleaned = cleaned;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
