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
public class SVLopMonHoc {
    private SinhVien sinhVien;
    private LopMonHoc lop;
    private double diemCC;
    private double diemGiuaKi;
    private double diemThi;
    private double diemCuoiKi;

    public SVLopMonHoc() {
    }

    public SVLopMonHoc(SinhVien sinhVien, LopMonHoc lop, double diemCC, double diemGiuaKi, double diemThi, double diemCuoiKi) {
        this.sinhVien = sinhVien;
        this.lop = lop;
        this.diemCC = diemCC;
        this.diemGiuaKi = diemGiuaKi;
        this.diemThi = diemThi;
        this.diemCuoiKi = diemCuoiKi;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public LopMonHoc getLop() {
        return lop;
    }

    public double getDiemCC() {
        return diemCC;
    }

    public double getDiemGiuaKi() {
        return diemGiuaKi;
    }

    public double getDiemThi() {
        return diemThi;
    }

    public double getDiemCuoiKi() {
        return diemCuoiKi;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public void setLop(LopMonHoc lop) {
        this.lop = lop;
    }

    public void setDiemCC(double diemCC) {
        this.diemCC = diemCC;
    }

    public void setDiemGiuaKi(double diemGiuaKi) {
        this.diemGiuaKi = diemGiuaKi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }

    public void setDiemCuoiKi(double diemCuoiKi) {
        this.diemCuoiKi = diemCuoiKi;
    }
    
}
