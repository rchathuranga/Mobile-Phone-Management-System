package lk.ijse.projectmp.dto;

public class CustomDTO {
    private int customerId;
    private String cName;
    private int contact;
    private int repairId;
    private int orderId;
    private double orderAmount;

    private String date;
    private int id;
    private int brandid;
    private int cataid;
    private String name;
    private int ram;
    private int storage;
    private String network;
    private int qty;
    private int warranty;
    private double sellingPrice;
    private double buyingPrice;
    private double total;
    private String completed;
    private double discount;

    private String catagoryName;
    private String brandName;

    private double orderIncome;
    private double repairIncome;
    private double totIncome;
    private double phoneExpense;
    private double itemExpence;
    private double repairPartExpence;
    private double totExpence;
    private double profit;

    public CustomDTO(double orderIncome, double repairIncome, double totIncome, double phoneExpense, double itemExpence, double repairPartExpence, double totExpence, double profit) {
        this.orderIncome = orderIncome;
        this.repairIncome = repairIncome;
        this.totIncome = totIncome;
        this.phoneExpense = phoneExpense;
        this.itemExpence = itemExpence;
        this.repairPartExpence = repairPartExpence;
        this.totExpence = totExpence;
        this.profit = profit;
    }

    public CustomDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomDTO(int customerId, String cName, int repairId, double buyingPrice, double total) {
        this.customerId = customerId;
        this.cName = cName;
        this.repairId = repairId;
        this.buyingPrice = buyingPrice;
        this.total = total;
    }

    public CustomDTO(int id, String name, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.sellingPrice = sellingPrice;
    }

    public CustomDTO(int id, String name, int qty, double sellingPrice) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.sellingPrice = sellingPrice;
    }

    public CustomDTO(int id, int brandid, int cataid, String name, int qty, int warranty, double sellingPrice, double buyingPrice) {
        this.id = id;
        this.brandid = brandid;
        this.cataid = cataid;
        this.name = name;
        this.qty = qty;
        this.warranty = warranty;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CustomDTO(int brandid, int cataid, String name, int ram, int storage, String network, int qty, int warranty, double sellingPrice, double buyingPrice) {
        this.brandid = brandid;
        this.cataid = cataid;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.network = network;
        this.qty = qty;
        this.warranty = warranty;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    public CustomDTO(int id, int brandid, int cataid, String name, int ram, int storage, String network, int qty, int warranty, double sellingPrice, double buyingPrice) {
        this.id = id;
        this.brandid = brandid;
        this.cataid = cataid;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.network = network;
        this.qty = qty;
        this.warranty = warranty;
        this.sellingPrice = sellingPrice;
        this.buyingPrice = buyingPrice;
    }

    public CustomDTO() {
    }



    public CustomDTO(int customerId, String cName, int repairId, int orderId, double orderAmount) {
        this.customerId = customerId;
        this.cName = cName;
        this.repairId = repairId;
        this.orderId = orderId;
        this.orderAmount = orderAmount;
    }

    public CustomDTO(int customerId, int orderId, double orderAmount) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.orderAmount = orderAmount;
    }

    public CustomDTO(int customerId, int repairId, int orderId) {
        this.customerId = customerId;
        this.repairId = repairId;
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    public int getCataid() {
        return cataid;
    }

    public void setCataid(int cataid) {
        this.cataid = cataid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getCatagoryName() {
        return catagoryName;
    }

    public void setCatagoryName(String catagoryName) {
        this.catagoryName = catagoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getOrderIncome() {
        return orderIncome;
    }

    public void setOrderIncome(double orderIncome) {
        this.orderIncome = orderIncome;
    }

    public double getRepairIncome() {
        return repairIncome;
    }

    public void setRepairIncome(double repairIncome) {
        this.repairIncome = repairIncome;
    }

    public double getTotIncome() {
        return totIncome;
    }

    public void setTotIncome(double totIncome) {
        this.totIncome = totIncome;
    }

    public double getPhoneExpense() {
        return phoneExpense;
    }

    public void setPhoneExpense(double phoneExpense) {
        this.phoneExpense = phoneExpense;
    }

    public double getItemExpence() {
        return itemExpence;
    }

    public void setItemExpence(double itemExpence) {
        this.itemExpence = itemExpence;
    }

    public double getRepairPartExpence() {
        return repairPartExpence;
    }

    public void setRepairPartExpence(double repairPartExpence) {
        this.repairPartExpence = repairPartExpence;
    }

    public double getTotExpence() {
        return totExpence;
    }

    public void setTotExpence(double totExpence) {
        this.totExpence = totExpence;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return id+" "+name;
    }
}
