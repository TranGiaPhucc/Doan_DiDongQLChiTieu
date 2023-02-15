package com.hufi.quanlychitieu;

public class ChiTietNguoiChiTieu {
    int id;
    String tenHoatDong;
    String username;
    int tien;

    public ChiTietNguoiChiTieu(int id, String tenHoatDong, String username, int tien) {
        this.id = id;
        this.tenHoatDong = tenHoatDong;
        this.username = username;
        this.tien = tien;
    }

    public ChiTietNguoiChiTieu(String tenHoatDong, String username, int tien) {
        this.tenHoatDong = tenHoatDong;
        this.username = username;
        this.tien = tien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHoatDong() {
        return tenHoatDong;
    }

    public void setTenHoatDong(String tenHoatDong) {
        this.tenHoatDong = tenHoatDong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    @Override
    public String toString() {
        return "ChiTietNguoiChiTieu{" +
                "id=" + id +
                ", tenHoatDong='" + tenHoatDong + '\'' +
                ", username='" + username + '\'' +
                ", tien=" + tien +
                '}';
    }
}
