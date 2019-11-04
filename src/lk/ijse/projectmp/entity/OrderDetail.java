package lk.ijse.projectmp.entity;

public class OrderDetail {
    private int id;
    private int orderid;
    private int itemid;
    private int phoneid;
    private int sdiscountid;
    private int qty;
    private double amount;
    private double discount;
    private double total;
    private int imei;

    public OrderDetail(int orderid, int phoneid, int sdiscountid, int qty, double total, int imei) {
        this.setOrderid(orderid);
        this.setPhoneid(phoneid);
        this.setSdiscountid(sdiscountid);
        this.setQty(qty);
        this.setTotal(total);
        this.setImei(imei);
    }

    public OrderDetail(int orderid, int itemid, int sdiscountid, int qty, double total) {
        this.setOrderid(orderid);
        this.setItemid(itemid);
        this.setSdiscountid(sdiscountid);
        this.setQty(qty);
        this.setTotal(total);
    }

    public OrderDetail(int orderid, int itemid, int phoneid, int sdiscountid, int qty, double amount, double discount, double total, int imei) {
        this.setOrderid(orderid);
        this.setItemid(itemid);
        this.setPhoneid(phoneid);
        this.setSdiscountid(sdiscountid);
        this.setQty(qty);
        this.setAmount(amount);
        this.setDiscount(discount);
        this.setTotal(total);
        this.setImei(imei);
    }

    public OrderDetail(int id, int orderid, int itemid, int phoneid, int sdiscountid, int qty,double amount,double discount,double total, int imei) {
        this.setId(id);
        this.setOrderid(orderid);
        this.setItemid(itemid);
        this.setPhoneid(phoneid);
        this.setSdiscountid(sdiscountid);
        this.setQty(qty);
        this.setAmount(amount);
        this.setDiscount(discount);
        this.setTotal(total);
        this.setImei(imei);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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

    public int getSdiscountid() {
        return sdiscountid;
    }

    public void setSdiscountid(int sdiscountid) {
        this.sdiscountid = sdiscountid;
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

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }
}
