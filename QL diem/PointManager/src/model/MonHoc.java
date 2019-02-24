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
public class MonHoc {
    private int id;
    private String tenMonHoc;
    private int soTinChi;
    private String moTa;
    private List<MonChuyenNganh> monChuyenNganhs = new ArrayList<>();

    public MonHoc() {
    }

    public MonHoc(int id, String tenMonHoc, int soTinChi, String moTa) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.moTa = moTa;
    }

    public int getId() {
        return id;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public List<MonChuyenNganh> getMonChuyenNganhs() {
        return monChuyenNganhs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setMonChuyenNganhs(List<MonChuyenNganh> monChuyenNganhs) {
        this.monChuyenNganhs = monChuyenNganhs;
    }
    
}
