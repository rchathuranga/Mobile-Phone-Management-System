package lk.ijse.projectmp.dto;

public class CustomerDTO {
    private int id;
    private String name;
    private int contact;
    private String nic;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, int contact, String nic) {
        this.name = name;
        this.contact = contact;
        this.nic = nic;
    }

    public CustomerDTO(int id, String name, int contact, String nic) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.nic = nic;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }


    @Override
    public String toString() {
        return name;
    }
}
