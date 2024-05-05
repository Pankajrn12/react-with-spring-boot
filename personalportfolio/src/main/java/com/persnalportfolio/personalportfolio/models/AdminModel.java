package com.persnalportfolio.personalportfolio.models;

public class AdminModel {
    private Long id;
    private String adminId;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "id=" + id +
                ", adminId='" + adminId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
