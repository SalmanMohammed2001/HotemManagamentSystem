package entity;

public class Meal {
    private String id;
    private String name;
    private double unitePrice;
    private int qtyOnHand ;

    public Meal() {
    }

    public Meal(String name, double unitePrice, int qtyOnHand) {
        this.name = name;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
    }

    public Meal(String id, String name, double unitePrice, int qtyOnHand) {
        this.id = id;
        this.name = name;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
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
}
