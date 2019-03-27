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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                sv = new SinhVien(idSV, tenSv, ngaySinh, queQuan, nienKhoa, queQuan);
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
        Connection connect = ConnectDB.getConnect();
        if (sinhVien == null) {
            return false;
        } else if (sinhVien.getTenSV().isEmpty() || sinhVien.getNgaySinh() == null || sinhVien.getQueQuan().isEmpty()) {
            return false;
        }
        try {
            connect.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(SVLopMonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "INSERT INTO tblSinhVien (idKhoaHoc, tenSV, ngaySinh,queQuan) VALUES(?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, sinhVien.getNienKhoa().getId());
            stmt.setString(2, sinhVien.getTenSV());
            stmt.setDate(3, sinhVien.getNgaySinh());
            stmt.setString(4, sinhVien.getQueQuan());
            stmt.execute();
            stmt.close();
            connect.commit();
            System.out.println("add success!");
            return true;
        } catch (SQLException ex) {
            try {
                connect.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(SVLopMonHocRepository.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("rollback add fail");
        }
        return false;
    }

    @Override
    public boolean delete(int idSV) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SinhVien> getByIdNienKhoa(NienKhoa nienKhoa) {
        List<SinhVien> sinhViens = new ArrayList<>();
        String sql = "SELECT tblSinhVien.id, tblSinhVien.idKhoaHoc, tblSinhVien.ngaySinh, tblSinhVien.queQuan, tblSinhVien.tenSV\n"
                + "FROM tblSinhVien, tblNienKhoa\n"
                + "WHERE tblSinhVien.idKhoaHoc = tblNienKhoa.id AND tblNienKhoa.id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, nienKhoa.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idSV = rs.getInt("id");
                String tenSv = rs.getString("tenSV");
                Date ngaySinh = rs.getDate("ngaySinh");
                String queQuan = rs.getString("queQuan");
                SinhVien sv = new SinhVien(idSV, tenSv, ngaySinh, queQuan, nienKhoa, queQuan);
                sinhViens.add(sv);
            }
        } catch (SQLException ex) {

        }
        return sinhViens;
    }

}
