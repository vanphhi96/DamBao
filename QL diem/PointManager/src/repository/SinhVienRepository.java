/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.INienKhoa;
import irepository.ISinhVien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.NienKhoa;
import model.SinhVien;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class SinhVienRepository implements ISinhVien {

    @Override
    public SinhVien getByID(int idSV) {
        SinhVien sv = null;
        String sql = "SELECT * FROM tblSinhVien WHERE id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idSV);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idKhoaHoc = rs.getInt("idKhoaHoc");
                String tenSv = rs.getString("tenSV");
                Date ngaySinh = rs.getDate("ngaySinh");
                String queQuan = rs.getString("queQuan");
                INienKhoa nienKhoaRepo = new NienKhoaRepository();
                NienKhoa nienKhoa = nienKhoaRepo.getByID(idKhoaHoc);
                sv = new SinhVien(idSV, tenSv, ngaySinh, queQuan, nienKhoa);
                stmt.close();
                rs.close();

            }
        } catch (SQLException ex) {

        }
        return sv;
    }

    @Override
    public List<SinhVien> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addSinhVien(SinhVien sinhVien) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idSV) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
