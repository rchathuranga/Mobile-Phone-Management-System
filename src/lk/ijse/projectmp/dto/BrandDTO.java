package lk.ijse.projectmp.dto;

public class BrandDTO implements SuperBrandCata{
    private int id;
    private String name;

    public BrandDTO() {
    }

    public BrandDTO(String name) {
        this.name = name;
    }

    public BrandDTO(int id) {
        this.id = id;
    }

    public BrandDTO(int id, String name) {
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
