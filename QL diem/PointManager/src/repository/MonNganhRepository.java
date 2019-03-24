/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IChuyenNganh;
import irepository.IMonHoc;
import irepository.IMonNganh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChuyenNganh;
import model.MonChuyenNganh;
import model.MonHoc;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class MonNganhRepository implements IMonNganh {

    @Override
    public MonChuyenNganh getMonChuyenNganh(MonHoc monHoc, ChuyenNganh chuyenNganh) {
        MonChuyenNganh monChuyenNganh = null;
        String sql = "SELECT * FROM tblMonNganh WHERE idMon = ? AND idChuyenNganh = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, monHoc.getId());
            stmt.setInt(2, chuyenNganh.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                monChuyenNganh = new MonChuyenNganh(id, chuyenNganh, monHoc);
            }
            return monChuyenNganh;
        } catch (SQLException ex) {
            Logger.getLogger(MonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monChuyenNganh;
    }

    @Override
    public MonChuyenNganh getByID(int id) {
        MonChuyenNganh monChuyenNganh = null;
        String sql = "SELECT * FROM tblMonNganh WHERE id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                monChuyenNganh = new MonChuyenNganh(id);
                rs.close();
                stmt.close();
            } else {
                System.out.println("No");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monChuyenNganh;
    }

    @Override
    public List<MonHoc> getMonNganhs(int idNganh) {
        List<MonHoc> monHocs = new ArrayList<>();
        String sql = "SELECT tblMonHoc.id, tblMonHoc.moTa, tblMonHoc.soTinChi, tblMonHoc.tenMonHoc\n"
                + " FROM tblMonNganh, tblMonHoc \n"
                + " WHERE tblMonNganh.idChuyenNganh = ? AND tblMonNganh.idMon = tblMonHoc.id";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idNganh);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idMon = rs.getInt("id");
                int soTinChi = rs.getInt("soTinChi");
                String moTa = rs.getString("moTa");
                String tenMonHoc = rs.getString("tenMonHoc");
                MonHoc mh = new MonHoc(idMon, tenMonHoc, soTinChi, moTa);
                monHocs.add(mh);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monHocs;

    }

    @Override
    public boolean addMonNganh(MonChuyenNganh monChuyenNganh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idMonNganh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
