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
public class LopMonHoc {
    private int idLop;
    private MonKhoaHoc monKhoaHoc;
    private int soLuong;
    private String phongHoc;
    private String tenLop;
    private List<SVLopMonHoc> svLopMonHocs = new ArrayList<>();

    public LopMonHoc(int idLop, MonKhoaHoc monKhoaHoc, int soLuong, String phongHoc,String tenLop) {
        this.idLop = idLop;
        this.monKhoaHoc = monKhoaHoc;
        this.soLuong = soLuong;
        this.phongHoc = phongHoc;
        this.tenLop = tenLop;
    }

    public List<SVLopMonHoc> getSvLopMonHocs() {
        return svLopMonHocs;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }
    
    public void setSvLopMonHocs(List<SVLopMonHoc> svLopMonHocs) {
        this.svLopMonHocs = svLopMonHocs;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public void setMonKhoaHoc(MonKhoaHoc monKhoaHoc) {
        this.monKhoaHoc = monKhoaHoc;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    public LopMonHoc() {
    }

    public int getIdLop() {
        return idLop;
    }

    public MonKhoaHoc getMonKhoaHoc() {
        return monKhoaHoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getPhongHoc() {
        return phongHoc;
    }
    
}
