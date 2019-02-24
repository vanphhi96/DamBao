/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.MonHoc;
import model.MonKhoaHoc;

/**
 *
 * @author vanph
 */
public interface IMonKhoaHoc {
    List<MonHoc> getMonKhoa(int idNienKhoa);
    MonKhoaHoc getMonKhoaHoc(int idMon, int idNienKhoa);
    MonKhoaHoc getByID(int idMonKhoaHoc);
    boolean addMonKhoaHoc(MonKhoaHoc monKhoaHoc);
    boolean delete(int idMonKhoaHoc);
}
