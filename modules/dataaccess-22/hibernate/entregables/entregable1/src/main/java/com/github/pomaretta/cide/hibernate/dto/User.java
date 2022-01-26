package com.github.pomaretta.cide.hibernate.dto;

import java.io.Serializable;

public class User implements Serializable {
    
    private int id;
    private String username;
    private String password;

    public User() {}

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
