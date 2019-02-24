/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vanph
 */
public class NienKhoa {
    private int id;
    private List<SinhVien> sinhViens = new ArrayList<>();
    private List<MonKhoaHoc> monKhoaHocs = new ArrayList<>();
    private ChuyenNganh chuyenNganh;
    private String nienKhoa;
    private String moTa;

    public NienKhoa(int id, ChuyenNganh chuyenNganh, String nienKhoa, String mota) {
        this.id = id;
        this.chuyenNganh = chuyenNganh;
        this.nienKhoa = nienKhoa;
        this.moTa = mota;
    }

    public int getId() {
        return id;
    }

    public List<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public List<MonKhoaHoc> getMonKhoaHocs() {
        return monKhoaHocs;
    }

    public ChuyenNganh getChuyenNganh() {
        return chuyenNganh;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSinhViens(List<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }

    public void setMonKhoaHocs(List<MonKhoaHoc> monKhoaHocs) {
        this.monKhoaHocs = monKhoaHocs;
    }

    public void setChuyenNganh(ChuyenNganh chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

   
    
}
