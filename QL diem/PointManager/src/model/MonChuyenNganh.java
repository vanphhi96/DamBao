/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vanph
 */
public class MonChuyenNganh {
    private int id;
    private ChuyenNganh chuyenNganh;
    private MonHoc monHoc;

    public MonChuyenNganh() {
    }

    public MonChuyenNganh(int id, ChuyenNganh chuyenNganh, MonHoc monHoc) {
        this.id = id;
        this.chuyenNganh = chuyenNganh;
        this.monHoc = monHoc;
    }

    public MonChuyenNganh(int id) {
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public ChuyenNganh getChuyenNganh() {
        return chuyenNganh;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChuyenNganh(ChuyenNganh chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }
    
    
}
