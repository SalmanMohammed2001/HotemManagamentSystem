package entity;

public class Room {
    private String id;
    private String roomType;
    private String available;
    private String cleanStatus;
    private double price;

    public Room() {
    }



    public Room(String roomType, String available, String cleanStatus , double price) {
        this.roomType = roomType;
        this.available = available;
        this.cleanStatus = cleanStatus;
        this.price = price;
    }

    public Room(String roomType, String available, String cleanStatus , double price,String id) {
        this.roomType = roomType;
        this.available = available;
        this.cleanStatus = cleanStatus;
        this.price = price;
        this.id=id;
    }

    public Room(String id, String roomType, String available, String cleanStatus, double price) {
        this.id = id;
        this.roomType = roomType;
        this.available = available;
        this.cleanStatus = cleanStatus;
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

    public String getCleanStatus() {
        return cleanStatus;
    }

    public void setCleanStatus(String cleaned) {
        this.cleanStatus = cleaned;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
