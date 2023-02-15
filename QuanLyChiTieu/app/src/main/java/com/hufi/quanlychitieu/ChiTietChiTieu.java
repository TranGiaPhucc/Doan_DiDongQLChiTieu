package com.hufi.quanlychitieu;

public class ChiTietChiTieu {
    String tenHoatDong;
    String ngay;
    String noiDung;
    int tongTien;

    public ChiTietChiTieu(String tenHoatDong, String ngay, String noiDung, int tongTien) {
        this.tenHoatDong = tenHoatDong;
        this.ngay = ngay;
        this.noiDung = noiDung;
        this.tongTien = tongTien;
    }

    public String getTenHoatDong() {
        return tenHoatDong;
    }

    public void setTenHoatDong(String tenHoatDong) {
        this.tenHoatDong = tenHoatDong;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "ChiTietChiTieu{" +
                "tenHoatDong='" + tenHoatDong + '\'' +
                ", ngay='" + ngay + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", tongTien=" + tongTien +
                '}';
    }
}
