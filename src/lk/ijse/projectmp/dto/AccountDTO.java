package lk.ijse.projectmp.dto;

public class AccountDTO {
    private int id;
    private String name;
    private String username;
    private String password;

    public AccountDTO(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public AccountDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public AccountDTO() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
