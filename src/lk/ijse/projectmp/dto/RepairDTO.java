package lk.ijse.projectmp.dto;

import java.util.ArrayList;

public class RepairDTO {
    private int repid;
    private int cid;
    private String date;
    private String time;
    private double total;
    private String phone;
    private String description;
    private int noofRepair;

    private ArrayList<RepairDetailDTO> repairDetail;

    private String customerName;

    public RepairDTO() {
    }

    public RepairDTO(int cid, double total, String phone, String description) {
        this.cid = cid;
        this.total = total;
        this.phone = phone;
        this.description = description;
    }

    public RepairDTO(int repid, String description) {
        this.repid = repid;
        this.description = description;
    }

    public RepairDTO(int cid, String date, String time, double total, String phone, String description) {
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.total = total;
        this.phone = phone;
        this.description = description;
    }

    public RepairDTO(int repid, int cid, String date, String time, double total, String phone, String description) {
        this.repid = repid;
        this.cid = cid;
        this.date = date;
        this.time = time;
        this.total = total;
        this.phone = phone;
        this.description = description;
    }

    public RepairDTO(int repid, int cid, String date, String time, String phone, String description, int noofRepair, double total) {
        this.setRepid(repid);
        this.setCid(cid);
        this.setDate(date);
        this.setTime(time);
        this.setPhone(phone);
        this.setDescription(description);
        this.setNoofRepair(noofRepair);
        this.setTotal(total);
    }


    public ArrayList<RepairDetailDTO> getRepairDetail() {
        return repairDetail;
    }

    public void setRepairDetail(ArrayList<RepairDetailDTO> repairDetail) {
        this.repairDetail = repairDetail;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNoofRepair() {
        return noofRepair;
    }

    public void setNoofRepair(int noofRepair) {
        this.noofRepair = noofRepair;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
