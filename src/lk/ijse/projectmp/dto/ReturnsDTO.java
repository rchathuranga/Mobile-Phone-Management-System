package lk.ijse.projectmp.dto;

public class ReturnsDTO {
    private int id;
    private int cid;
    private int itemid;
    private int phoneid;
    private String reason;
    private double price;

    private String customerName;
    private String itemName;
    private String phoneName;

    public ReturnsDTO() {
    }

    public ReturnsDTO(int cid, int itemid, int phoneid, String reason, double price) {
        this.cid = cid;
        this.itemid = itemid;
        this.phoneid = phoneid;
        this.reason = reason;
        this.price = price;
    }

    public ReturnsDTO(int id, int cid, String reason, double price) {
        this.id = id;
        this.cid = cid;
        this.reason = reason;
        this.price = price;
    }

    public ReturnsDTO(int id, int cid, int itemid, int phoneid, String reason, double price) {
        this.id = id;
        this.cid = cid;
        this.itemid = itemid;
        this.phoneid = phoneid;
        this.reason = reason;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(int phoneid) {
        this.phoneid = phoneid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
