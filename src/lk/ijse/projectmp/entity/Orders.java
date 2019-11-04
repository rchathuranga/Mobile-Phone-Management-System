package lk.ijse.projectmp.entity;

public class Orders {
    private int id;
    private int cid;
    private String date;
    private String time;
    private double amount;
    private double discount;
    private double total;

    public Orders() {
    }

    public Orders(int cid, double amount, double discount, double total) {
        this.cid = cid;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public Orders(int cid, String date, String time, double amount, double discount) {
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
    }

    public Orders(int id, int cid, String date, String time, double amount, double discount) {
        this.id = id;
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
    }

    public Orders(int cid, String date, String time, double amount, double discount, double total) {
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public Orders(int id, int cid, String date, String time, double amount, double discount, double total) {
        this.id = id;
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
