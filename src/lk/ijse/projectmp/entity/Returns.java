package lk.ijse.projectmp.entity;

public class Returns {
    private int id;
    private int cid;
    private int itemid;
    private int phoneid;
    private String reason;
    private double price;

    public Returns() {
    }

    public Returns(int cid, int itemid, int phoneid, String reason, double price) {
        this.cid = cid;
        this.itemid = itemid;
        this.phoneid = phoneid;
        this.reason = reason;
        this.price = price;
    }

    public Returns(int id, int cid, int itemid, int phoneid, String reason, double price) {
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
}
