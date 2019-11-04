package lk.ijse.projectmp.entity;

public class RepairPart {
    private int partid;
    private String partName;
    private int qty;
    private double buyingPrice;
    private double sellingPrice;

    public RepairPart(int partid, String partName, double buyingPrice, double sellingPrice, int qty) {
        this.partid = partid;
        this.partName = partName;
        this.qty = qty;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public RepairPart(String partName, double buyingPrice, double sellingPrice, int qty) {
        this.partName = partName;
        this.qty = qty;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public RepairPart() {
    }

    public int getPartid() {
        return partid;
    }

    public void setPartid(int partid) {
        this.partid = partid;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
