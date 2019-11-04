package lk.ijse.projectmp.dto;

public class OrderDetailDTO {
    private int id;
    private int orderid;
    private String customerName;
    private int customerContact;
    private int itemid;
    private String itemName;
    private int phoneid;
    private String phoneName;
    private int sdicountid;
    private int qty;
    private double amount;
    private double discount;
    private double total;
    private int imei;


    public OrderDetailDTO(int orderid, int phoneid, int sdicountid, int qty, double amount, double discount, double total, int imei) {
        this.orderid = orderid;
        this.phoneid = phoneid;
        this.sdicountid = sdicountid;
        this.qty = qty;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
        this.imei = imei;
    }

    public OrderDetailDTO(int orderid, int itemid, int sdicountid, int qty, double amount, double discount, double total) {
        this.orderid = orderid;
        this.itemid = itemid;
        this.sdicountid = sdicountid;
        this.qty = qty;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public OrderDetailDTO(int id, int orderid, int itemid, int phoneid, int sdicountid, int qty, double total, int imei) {
        this.id = id;
        this.orderid = orderid;
        this.itemid = itemid;
        this.phoneid = phoneid;
        this.sdicountid = sdicountid;
        this.qty = qty;
        this.total = total;
        this.imei = imei;
    }

    public OrderDetailDTO(int id, int orderid, int itemid, int phoneid, int sdicountid, int qty, double amount, double discount, double total, int imei) {
        this.id = id;
        this.orderid = orderid;
        this.itemid = itemid;
        this.phoneid = phoneid;
        this.sdicountid = sdicountid;
        this.qty = qty;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
        this.imei = imei;
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

    public int getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(int customerContact) {
        this.customerContact = customerContact;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public int getSdicountid() {
        return sdicountid;
    }

    public void setSdicountid(int sdicountid) {
        this.sdicountid = sdicountid;
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
