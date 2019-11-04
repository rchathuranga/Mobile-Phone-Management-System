package lk.ijse.projectmp.dto;

public class RepairServiceDTO {
    private int rsid;
    private int rpid;
    private String name;
    private double price;
    private RepairPartDTO part;

    public RepairServiceDTO(String name, double price) {
        this.setName(name);
        this.setPrice(price);
    }

    public RepairServiceDTO() {
    }



    public RepairServiceDTO(int rpid, String name, double price) {
        this.setRpid(rpid);
        this.setName(name);
        this.setPrice(price);
    }

    public RepairServiceDTO(int rsid, int rpid, String name, double price) {
        this.setRsid(rsid);
        this.setRpid(rpid);
        this.setName(name);
        this.setPrice(price);
    }

    public int getRsid() {
        return rsid;
    }

    public void setRsid(int rsid) {
        this.rsid = rsid;
    }

    public int getRpid() {
        return rpid;
    }

    public void setRpid(int rpid) {
        this.rpid = rpid;
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

    @Override
    public String toString() {
        return getRsid()+" "+getName();
    }

    public RepairPartDTO getPart() {
        return part;
    }

    public void setPart(RepairPartDTO part) {
        this.part = part;
    }
}
