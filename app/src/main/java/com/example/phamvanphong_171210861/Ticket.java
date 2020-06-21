package com.example.phamvanphong_171210861;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int Id;
    private String GaDen;
    private String GaDi;
    private long DonGia;
    private int TheLoai;

    public Ticket(int id, String gaDen, String gaDi, long donGia, int theLoai) {
        Id = id;
        GaDen = gaDen;
        GaDi = gaDi;
        DonGia = donGia;
        TheLoai = theLoai;
    }

    public int getId() {
        return Id;
    }

    public String getGaDen() {
        return GaDen;
    }

    public String getGaDi() {
        return GaDi;
    }

    public long getDonGia() {
        return DonGia;
    }

    public int isTheLoai() {
        return TheLoai;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setGaDen(String gaDen) {
        GaDen = gaDen;
    }

    public void setGaDi(String gaDi) {
        GaDi = gaDi;
    }

    public void setDonGia(long donGia) {
        DonGia = donGia;
    }

    public void setTheLoai(int theLoai) {
        TheLoai = theLoai;
    }
}
