/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IMonHoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MonHoc;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class MonHocRepository implements IMonHoc {

    @Override
    public MonHoc getMHByID(int idMonHoc) {
        MonHoc monHoc = null;
        String sql = "SELECT * FROM tblMonHoc WHERE id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idMonHoc);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                monHoc = new MonHoc(idMonHoc, rs.getString("tenMonHoc"),rs.getInt("soTinChi"), rs.getString("moTa"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monHoc;
    }

    @Override
    public List<MonHoc> getAll() {
        List<MonHoc> monHocs = new ArrayList<>();
           try {
             String sql = "SELECT * FROM tblMonHoc";
            Connection connect = ConnectDB.getConnect();
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int soTinChi = rs.getInt("soTinChi");
                String tenMonHoc = rs.getString("tenMonHoc");
                String mota = rs.getString("moTa");
                MonHoc monHoc = new MonHoc(id, tenMonHoc, soTinChi, mota);
                monHocs.add(monHoc);
            }
           
            return monHocs;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenNganhRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return monHocs;
    }

    @Override
    public boolean addMonHoc(MonHoc monHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idMonHoc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
