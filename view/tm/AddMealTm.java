package view.tm;

import javafx.scene.control.Button;

public class AddMealTm {
    private String id;
    private String name;
    private double unitePrice;
    private int  qty;
    private double total;
    private Button btn;

    public AddMealTm() {
    }

    public AddMealTm(String id, String name, double unitePrice, int qty, double total, Button btn) {
        this.id = id;
        this.name = name;
        this.unitePrice = unitePrice;
        this.qty = qty;
        this.total = total;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
