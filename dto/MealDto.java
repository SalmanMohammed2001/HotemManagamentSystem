package dto;

public class MealDto {
    private String id;
    private String name;
    private double unitePrice;
    private int qtyOnHand ;

    public MealDto() {
    }

    public MealDto(String name, double unitePrice, int qtyOnHand) {
        this.name = name;
        this.unitePrice = unitePrice;
        this.qtyOnHand = qtyOnHand;
    }

    public MealDto(String id, String name, double unitePrice, int qtyOnHand) {
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
