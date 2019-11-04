package lk.ijse.projectmp.dto;

public class SDiscountDTO {
    private int id;
    private String startDate;
    private String endDate;
    private int days;
    private int cata;
    private int brand;
    private double amount;
    private String percentage;

    private String brandName;
    private String catagoryName;

    public SDiscountDTO() {
    }

    public SDiscountDTO(String startDate, String endDate, int cata, int brand, double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.cata = cata;
        this.brand = brand;
        this.amount = amount;
    }

    public SDiscountDTO(int id, String startDate, String endDate, int cata, int brand, double amount, String percentage) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cata = cata;
        this.brand = brand;
        this.amount = amount;
        this.percentage = percentage;
    }

    public SDiscountDTO(int id, String startDate, String endDate, int days, int cata, int brand, double amount, String percentage) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.cata = cata;
        this.brand = brand;
        this.amount = amount;
        this.percentage = percentage;
    }

    public SDiscountDTO(int id, String startDate, String endDate, int days, int cata, int brand, double amount) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.cata = cata;
        this.brand = brand;
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

    public int getCata() {
        return cata;
    }

    public void setCata(int cata) {
        this.cata = cata;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }
}
