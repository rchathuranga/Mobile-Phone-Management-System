package lk.ijse.projectmp.entity;

public class Customer {
    private String date;
    private int id;
    private String name;
    private int contact;
    private String NIC;

    public Customer(String date, int id, String name, int contact, String NIC) {
        this.date = date;
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.NIC = NIC;
    }

    public Customer(int id, String name, int contact, String NIC) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.NIC = NIC;
    }

    public Customer(String name, int contact, String NIC) {
        this.name = name;
        this.contact = contact;
        this.NIC = NIC;
    }

    public Customer(String name, int contact) {
        this.name = name;
        this.contact = contact;
    }

    public Customer() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
}
