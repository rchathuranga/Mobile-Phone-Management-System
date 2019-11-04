package lk.ijse.projectmp.dto;

public class CatagoryDTO implements SuperBrandCata{
    private int id;
    private String name;

    public CatagoryDTO() {
    }

    public CatagoryDTO(int id) {
        this.id = id;
    }

    public CatagoryDTO(String name) {
        this.name = name;
    }

    public CatagoryDTO(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}
