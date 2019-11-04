package lk.ijse.projectmp.entity;

public class SDiscount {
    private int id;
    private String startDate;
    private String endDate;
    private int days;
    private int cataId;
    private int brandId;
    private double amount;

    public SDiscount() {
    }

    public SDiscount(String startDate, String endDate, int days, int cataId, int brandId, double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.cataId = cataId;
        this.brandId = brandId;
        this.amount = amount;
    }

    public SDiscount(int id, String startDate, String endDate, int cataId, int brandId, double amount) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cataId = cataId;
        this.brandId = brandId;
        this.amount = amount;
    }

    public SDiscount(int id, String startDate, String endDate, int days, int cataId, int brandId, double amount) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.cataId = cataId;
        this.brandId = brandId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getCataId() {
        return cataId;
    }

    public void setCataId(int cataId) {
        this.cataId = cataId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
