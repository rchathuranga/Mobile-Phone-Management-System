package lk.ijse.projectmp.dto;

import java.util.ArrayList;

public class OrdersDTO {
    private int id;
    private int cid;
    private String customerName;
    private int customerContact;
    private String date;
    private String time;
    private int qty;
    private double amount;
    private double discount;
    private double total;
    private ArrayList<OrderDetailDTO> orderDetails;

    public OrdersDTO() {
    }

    public OrdersDTO(int cid, double amount, double discount,double total) {
        this.cid = cid;
        this.amount = amount;
        this.discount = discount;
        this.total=total;
    }

    public OrdersDTO(int cid, String date, String time, double amount, double discount) {
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
    }

    public OrdersDTO(int id, int cid, String date, String time, double amount, double discount) {
        this.id = id;
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
    }

    public OrdersDTO(int id, int cid, String date, String time, double amount, double discount, double total) {
        this.id = id;
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.discount = discount;
        this.total = total;
    }

    public ArrayList<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(int customerContact) {
        this.customerContact = customerContact;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
