package lk.ijse.projectmp.entity;

public class Item {
    private int id;
    private int brandid;
    private int cataid;
    private String name;
    private int warranty;
    private int qty;
    private double buyingPrice;
    private double sellingPrice;

    public Item() {
    }

    public Item(int id, String name, int qty) {
        this.id = id;
        this.name = name;
        this.qty = qty;
    }

    public Item(int brandid, int cataid, String name, int warranty, int qty, double buyingPrice, double sellingPrice) {
        this.brandid = brandid;
        this.cataid = cataid;
        this.name = name;
        this.warranty = warranty;
        this.qty = qty;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public Item(int id, int brandid, int cataid, String name, int warranty, int qty, double buyingPrice, double sellingPrice) {
        this.id = id;
        this.brandid = brandid;
        this.cataid = cataid;
        this.name = name;
        this.warranty = warranty;
        this.qty = qty;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public int getCataid() {
        return cataid;
    }

    public void setCataid(int cataid) {
        this.cataid = cataid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
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

