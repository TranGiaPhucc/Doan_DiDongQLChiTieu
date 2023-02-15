package com.hufi.quanlychitieu;

public class BanBe {
    int id;
    String username;
    String usernamefriend;
    int checkChiTietCustom2;
    int tongTien;

    public BanBe(int id, String username, String usernamefriend) {
        this.id = id;
        this.username = username;
        this.usernamefriend = usernamefriend;
    }

    public BanBe(String username, String usernamefriend, int checkChiTietCustom2) {
        this.username = username;
        this.usernamefriend = usernamefriend;
        this.checkChiTietCustom2 = checkChiTietCustom2;
    }

    public BanBe(String username, String usernamefriend, int checkChiTietCustom2, int tongTien) {
        this.username = username;
        this.usernamefriend = usernamefriend;
        this.checkChiTietCustom2 = checkChiTietCustom2;
        this.tongTien = tongTien;
    }

    public BanBe(int id, String username, String usernamefriend, int checkChiTietCustom2, int tongTien) {
        this.id = id;
        this.username = username;
        this.usernamefriend = usernamefriend;
        this.checkChiTietCustom2 = checkChiTietCustom2;
        this.tongTien = tongTien;
    }

    public BanBe(String username, String usernamefriend) {
        this.username = username;
        this.usernamefriend = usernamefriend;
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

    public String getUsernamefriend() {
        return usernamefriend;
    }

    public void setUsernamefriend(String usernamefriend) {
        this.usernamefriend = usernamefriend;
    }

    public int getCheckChiTietCustom2() {
        return checkChiTietCustom2;
    }

    public void setCheckChiTietCustom2(int checkChiTietCustom2) {
        this.checkChiTietCustom2 = checkChiTietCustom2;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "BanBe{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", usernamefriend='" + usernamefriend + '\'' +
                ", checkChiTietCustom2=" + checkChiTietCustom2 +
                ", tongTien=" + tongTien +
                '}';
    }
}
