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
public class ChuyenNganh {
    private int id;
    private String tenChuyenNganh;
    private String mota;
    private List<NienKhoa> nienKhoas = new ArrayList<>();

    public ChuyenNganh() {
    }

    public ChuyenNganh(int id, String tenChuyenNganh, String mota) {
        this.id = id;
        this.tenChuyenNganh = tenChuyenNganh;
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public String getTenChuyenNganh() {
        return tenChuyenNganh;
    }

    public String getMota() {
        return mota;
    }

    public List<NienKhoa> getNienKhoas() {
        return nienKhoas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenChuyenNganh(String tenChuyenNganh) {
        this.tenChuyenNganh = tenChuyenNganh;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setNienKhoas(List<NienKhoa> nienKhoas) {
        this.nienKhoas = nienKhoas;
    }
    
}
