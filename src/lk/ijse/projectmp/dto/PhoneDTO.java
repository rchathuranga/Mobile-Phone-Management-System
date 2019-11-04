package lk.ijse.projectmp.dto;

public class PhoneDTO{
    private int id;
    private int brandid;
    private String name;
    private int ram;
    private int storage;
    private String network;
    private int qty;
    private int warranty;
    private double sellingPrice;
    private double buyingPrice;

    public PhoneDTO() {
    }

    public PhoneDTO(int brandid, String name, int ram, int storage, String network, int qty, int warranty, double sellingPrice, double buyingPrice) {
        this.brandid = brandid;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.network = network;
        this.qty = qty;
        this.warranty = warranty;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    public PhoneDTO(int id, int brandid, String name, int ram, int storage, String network, int qty, int warranty, double sellingPrice, double buyingPrice) {
        this.id = id;
        this.brandid = brandid;
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

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
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

    @Override
    public String toString() {
        return name;
    }
}
