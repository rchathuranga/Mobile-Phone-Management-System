package lk.ijse.projectmp.entity;

public class RepairDetail {
    private int id;
    private int repairId;
    private int repairServiceId;
    private double amount;
    private String completed;

    public RepairDetail() {
    }

    public RepairDetail(int repairId, int repairServiceId, double amount) {
        this.repairId = repairId;
        this.repairServiceId = repairServiceId;
        this.amount = amount;
    }

    public RepairDetail(int repairId, int repairServiceId, double amount, String completed) {
        this.repairId = repairId;
        this.repairServiceId = repairServiceId;
        this.amount = amount;
        this.completed = completed;
    }

    public RepairDetail(int id, int repairId, int repairServiceId, double amount, String completed) {
        this.id = id;
        this.repairId = repairId;
        this.repairServiceId = repairServiceId;
        this.amount = amount;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getRepairServiceId() {
        return repairServiceId;
    }

    public void setRepairServiceId(int repairServiceId) {
        this.repairServiceId = repairServiceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }
}
