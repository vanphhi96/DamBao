/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IMonKhoaHoc;
import java.util.List;
import model.MonHoc;
import model.MonKhoaHoc;

/**
 *
 * @author vanph
 */
public class MonKhoaHocRepository implements IMonKhoaHoc{

    @Override
    public List<MonHoc> getMonKhoa(int idNienKhoa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MonKhoaHoc getMonKhoaHoc(int idMon, int idNienKhoa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MonKhoaHoc getByID(int idMonKhoaHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addMonKhoaHoc(MonKhoaHoc monKhoaHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idMonKhoaHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
