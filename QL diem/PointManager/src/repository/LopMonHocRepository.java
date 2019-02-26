/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.ILopMonHoc;
import irepository.IMonKhoaHoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LopMonHoc;
import model.MonKhoaHoc;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class LopMonHocRepository implements ILopMonHoc {

    @Override
    public LopMonHoc getByID(int idLopMonHoc) {
        LopMonHoc lopMonHoc = null;
        String sql = "SELECT * FROM tblLopMonHoc WHERE id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idLopMonHoc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int idMonKhoaHoc = rs.getInt("idMonKhoaHoc");
                String phongHoc = rs.getString("phongHoc");
                String tenLop = rs.getString("tenLop");
                int soLuong = rs.getInt("soLuong");
                IMonKhoaHoc iMonKhoaHoc = new MonKhoaHocRepository();
                MonKhoaHoc monKhoaHoc = iMonKhoaHoc.getByID(idMonKhoaHoc);
                lopMonHoc = new LopMonHoc(idLopMonHoc, monKhoaHoc, soLuong, phongHoc, tenLop);
                stmt.close();
                rs.close();
                return lopMonHoc;
            }

        } catch (SQLException ex) {

        }
        return lopMonHoc;
    }

    @Override
    public List<LopMonHoc> getLopMonHocs(int idMonKhoaHoc) {
        List<LopMonHoc> hocs = new ArrayList<>();
        String sql = "SELECT tblLopMonHoc.id, tblLopMonHoc.idMonKhoaHoc, tblLopMonHoc.phongHoc, tblLopMonHoc.soLuong, tblLopMonHoc.tenLop "
                + "FROM tblLopMonHoc, tblMonKhoaHoc"
                + " WHERE tblMonKhoaHoc.id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idMonKhoaHoc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String phongHoc = rs.getString("phongHoc");
                String tenLop = rs.getString("tenLop");
                int soLuong = rs.getInt("soLuong");
                IMonKhoaHoc iMonKhoaHoc = new MonKhoaHocRepository();
                MonKhoaHoc monKhoaHoc = iMonKhoaHoc.getByID(idMonKhoaHoc);
                LopMonHoc lopMonHoc = new LopMonHoc(id, monKhoaHoc, soLuong, phongHoc, tenLop);
                hocs.add(lopMonHoc);
            }
            return hocs;
        } catch (SQLException ex) {

        }
        return hocs;
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
