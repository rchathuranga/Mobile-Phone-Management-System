package lk.ijse.projectmp.entity;

public class Custom {
    private int cId;
    private String cName;
    private int repairId;
    private int orderId;
    private double orderAmount;
    private String date;

    private int id;
    private String name;
    private int qty;
    private double sellingPrice;



    public Custom(int id, String name, int qty, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
    }

    public Custom(){
    }

    public Custom(int cId, String cName, int repairId, int orderId, double orderAmount) {
        this.cId = cId;
        this.cName = cName;
        this.repairId = repairId;
        this.orderId = orderId;
        this.orderAmount = orderAmount;
    }

    public Custom(int cId, int orderId, double orderAmount,String date) {
        this.cId = cId;
        this.orderId = orderId;
        this.orderAmount = orderAmount;
        this.date=date;
    }

    public Custom(int cId, int orderId) {
        this.cId = cId;
        this.orderId = orderId;
    }

    public Custom(int cId, int repairId, int orderId) {
        this.cId = cId;
        this.repairId = repairId;
        this.orderId = orderId;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}
