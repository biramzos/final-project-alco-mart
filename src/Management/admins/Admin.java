package Management.admins;

import Management.common.Model;
import Management.common.Role;

public class Admin implements Model {
    private int id;
    private String username;

    private String password;
    private String address;
    private String phone;

    private int role = Role.ADMIN.getId();

    public Admin(){}

    public Admin(int id, String username, String password, String address, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString () {
        return "{\n\tID: " + id +
                "\n\tUsername: " + username +
                "\n\tPassword: " + password +
                "\n\tAddress: " + address +
                "\n\tPhone number: " + phone +
                "\n}";
    }
}
