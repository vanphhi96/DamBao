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
    private double diemCC = 0;
    private double diemGiuaKi = 0;
    private double diemThi = 0;
    private double diemCuoiKi = 0;
    private String ghiChu = " ";
    private double diemTB =0;
    public SVLopMonHoc() {
    }

    public SVLopMonHoc(SinhVien sinhVien, LopMonHoc lop, double diemCC, double diemGiuaKi, double diemThi, String ghiChu) {
        this.sinhVien = sinhVien;
        this.lop = lop;
        this.diemCC = diemCC;
        this.diemGiuaKi = diemGiuaKi;
        this.diemThi = diemThi;
        this.ghiChu = ghiChu;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
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
    
    public double getDiemTB(){
        return (diemCC*0.1)+(diemGiuaKi*0.2)+(diemThi*0.7);
    }
    
    public String getStatus(){
        if(getDiemTB()>4){
            return "Đạt";
        }
        else{
            return "Không đạt";
        }
    }

}
