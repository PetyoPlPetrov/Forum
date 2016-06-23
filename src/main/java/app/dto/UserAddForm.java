package app.dto;

import app.entity.Role;

/**
 * Created by PetyoPetrov on 24.05.2016 г..
 */
public class UserAddForm {
    private String username;
    private String password;
    private int id;

    private Role role = Role.USER;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

