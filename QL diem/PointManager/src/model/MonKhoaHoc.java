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
public class MonKhoaHoc {
    private int idMonKhoaHoc;
    private MonChuyenNganh monChuyenNganh;
    private NienKhoa nienKhoa;
    private List<LopMonHoc> lopMonHocs = new ArrayList<>();

  
    public MonKhoaHoc() {
    }

    public MonKhoaHoc(int idMonKhoaHoc, MonChuyenNganh monChuyenNganh, NienKhoa nienKhoa) {
        this.idMonKhoaHoc = idMonKhoaHoc;
        this.monChuyenNganh = monChuyenNganh;
        this.nienKhoa = nienKhoa;
    }

    public int getIdMonKhoaHoc() {
        return idMonKhoaHoc;
    }

    public MonChuyenNganh getMonChuyenNganh() {
        return monChuyenNganh;
    }

    public NienKhoa getNienKhoa() {
        return nienKhoa;
    }

    public void setIdMonKhoaHoc(int idMonKhoaHoc) {
        this.idMonKhoaHoc = idMonKhoaHoc;
    }

    public void setMonChuyenNganh(MonChuyenNganh monChuyenNganh) {
        this.monChuyenNganh = monChuyenNganh;
    }

    public void setNienKhoa(NienKhoa nienKhoa) {
        this.nienKhoa = nienKhoa;
    }
      public void setLopMonHocs(List<LopMonHoc> lopMonHocs) {
        this.lopMonHocs = lopMonHocs;
    }

    public List<LopMonHoc> getLopMonHocs() {
        return lopMonHocs;
    }
}
