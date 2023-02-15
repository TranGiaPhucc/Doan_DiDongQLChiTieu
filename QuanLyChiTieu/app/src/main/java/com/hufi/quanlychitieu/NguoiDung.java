package com.hufi.quanlychitieu;

public class NguoiDung {
    String username;
    String password;
    String name;
    String sdt;

    public NguoiDung(String username) {
        this.username = username;
    }

    public NguoiDung(String username, String password, String name, String sdt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sdt = sdt;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
