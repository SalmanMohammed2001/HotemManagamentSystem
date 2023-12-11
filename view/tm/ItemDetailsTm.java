package view.tm;

public class ItemDetailsTm {
    private String id;
    private double unitePrice;
    private int qty;
    private double total;


    public ItemDetailsTm() {
    }

    public ItemDetailsTm(String id, double unitePrice, int qty, double total) {
        this.id = id;
        this.unitePrice = unitePrice;
        this.qty = qty;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
