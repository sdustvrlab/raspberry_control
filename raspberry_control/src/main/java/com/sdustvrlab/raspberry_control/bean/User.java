package com.sdustvrlab.raspberry_control.bean;

public class User {
    private int id;
    private String username;
    private String password;
    private Integer admin;

    public User() {
    }

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

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public User(int id, String username, String password, Integer admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }
}
