package view.tm;

import javafx.scene.control.Button;

public class RoomTm {
    private String id;
    private String roomType;
    private String available;
    private String cleaned;
    private double price;
    private Button btn;

    public RoomTm(String id, String roomType, String available, String cleaned, double price, Button btn) {
        this.id = id;
        this.roomType = roomType;
        this.available = available;
        this.cleaned = cleaned;
        this.price = price;
        this.btn = btn;
    }

    public RoomTm() {
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
