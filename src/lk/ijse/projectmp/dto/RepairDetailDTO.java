package lk.ijse.projectmp.dto;

public class RepairDetailDTO {
    private int id;
    private int repairId;
    private String customerName;
    private int customerContact;
    private String phone;
    private int repairServiceId;
    private double amount;
    private String completed;
    private int qty;

    private String serviceName;
    private double servicePrice;
    private int partId;
    private String partName;
    private double partPrice;

    public RepairDetailDTO(int repairServiceId, double amount) {
        this.repairServiceId = repairServiceId;
        this.amount = amount;
    }

    public RepairDetailDTO(int repairServiceId, String serviceName, double servicePrice, int partId) {
        this.repairServiceId = repairServiceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.partId = partId;
    }

    public RepairDetailDTO() {
    }

    public RepairDetailDTO(int repairId, int repairServiceId, double amount, String completed) {
        this.repairId = repairId;
        this.repairServiceId = repairServiceId;
        this.amount = amount;
        this.completed = completed;
    }

    public RepairDetailDTO(int id, int repairId, int repairServiceId, double amount, String completed) {
        this.id = id;
        this.repairId = repairId;
        this.repairServiceId = repairServiceId;
        this.amount = amount;
        this.completed = completed;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
