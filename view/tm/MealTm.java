package view.tm;

import javafx.scene.control.Button;

public class MealTm {
    private String id;
    private String name;
    private double unitePrice;
    private int qtyOnHand ;
    private Button btn;


    public MealTm() {
    }

    public MealTm(String id, String name, double unitePrice, int qtyOnHand, Button btn) {
        this.id = id;
        this.name = name;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
