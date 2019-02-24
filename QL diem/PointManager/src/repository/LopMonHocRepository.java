/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.ILopMonHoc;
import java.util.List;
import model.LopMonHoc;

/**
 *
 * @author vanph
 */
public class LopMonHocRepository implements ILopMonHoc{

    @Override
    public LopMonHoc getByID(int idLopMonHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LopMonHoc> getLopMonHocs(int idMonKhoaHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addLopMonHoc(LopMonHoc lopMonHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idLopMonHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
