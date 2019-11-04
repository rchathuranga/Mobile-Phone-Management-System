package lk.ijse.projectmp.entity;

public class RepairService {
    private int repairServiceId;
    private int repairPartId;
    private String name;
    private double price;

    public RepairService() {
    }

    public RepairService(int repairPartId, String name, double price) {
        this.setRepairPartId(repairPartId);
        this.setName(name);
        this.setPrice(price);
    }

    public RepairService(int repairServiceId, int repairPartId, String name, double price) {
        this.setRepairServiceId(repairServiceId);
        this.setRepairPartId(repairPartId);
        this.setName(name);
        this.setPrice(price);
    }



    public int getRepairServiceId() {
        return repairServiceId;
    }

    public void setRepairServiceId(int repairServiceId) {
        this.repairServiceId = repairServiceId;
    }

    public int getRepairPartId() {
        return repairPartId;
    }

    public void setRepairPartId(int repairPartId) {
        this.repairPartId = repairPartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}