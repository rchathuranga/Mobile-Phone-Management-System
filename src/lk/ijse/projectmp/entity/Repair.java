package lk.ijse.projectmp.entity;

public class Repair {
    private int repid;
    private int cid;
    private String date;
    private String time;
    private double amount;
    private String phone;
    private String description;

    public Repair() {
    }

    public Repair(int repid, String description) {
        this.repid = repid;
        this.description = description;
    }

    public Repair(int cid, double amount, String phone, String description) {
        this.cid = cid;
        this.amount = amount;
        this.phone = phone;
        this.description = description;
    }

    public Repair(int cid, String date, String time, double amount, String phone, String description) {
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.phone = phone;
        this.description = description;
    }

    public Repair(int repid, int cid, String date, String time,double amount, String phone, String description) {
        this.setRepid(repid);
        this.setCid(cid);
        this.setDate(date);
        this.setTime(time);
        this.setAmount(amount);
        this.setPhone(phone);
        this.setDescription(description);
    }

    public int getRepid() {
        return repid;
    }

    public void setRepid(int repid) {
        this.repid = repid;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
