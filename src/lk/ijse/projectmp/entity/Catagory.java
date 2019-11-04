package lk.ijse.projectmp.entity;

public class Catagory {
    private int id;
    private String name;

    public Catagory() {
    }

    public Catagory(String name) {
        this.name = name;
    }

    public Catagory(int id, String name) {
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
}
