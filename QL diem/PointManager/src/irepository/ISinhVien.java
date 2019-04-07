/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.NienKhoa;
import model.SinhVien;

/**
 *
 * @author vanph
 */
public interface ISinhVien {
    SinhVien getByID(int idSV);
    List<SinhVien> getAll();
    boolean addSinhVien(SinhVien sinhVien);
    boolean delete(int idSV);
    List<SinhVien> getByIdNienKhoa(NienKhoa nienKhoa);
    boolean updateSinhVien(SinhVien sinhVien);
}
