package lk.ijse.projectmp.entity;

public class Phone {
    private int id;
    private int brandId;
    private String name;
    private int ram;
    private int storage;
    private String network;
    private int qty;
    private int warranty;
    private double sellingPrice;
    private double buyingPrice;

    public Phone() {
    }

    public Phone(int brandId, String name, int ram, int storage, String network, int qty, int warranty, double buyingPrice,double sellingPrice) {
        this.brandId = brandId;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.network = network;
        this.qty = qty;
        this.warranty = warranty;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    public Phone(int id, int brandId, String name, int ram, int storage, String network, int qty, int warranty, double buyingPrice,double sellingPrice) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.network = network;
        this.qty = qty;
        this.warranty = warranty;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }
}
