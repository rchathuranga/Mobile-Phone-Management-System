package lk.ijse.projectmp.entity;

public class Service {
    private int id;
    private String name;
    private double servicePrice;

    public Service(int id, String name, double servicePrice) {
        this.id = id;
        this.name = name;
        this.servicePrice = servicePrice;
    }

    public Service(String name, double servicePrice) {
        this.name = name;
        this.servicePrice = servicePrice;
    }

    public Service() {
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

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
