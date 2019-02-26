/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.ILopMonHoc;
import irepository.ISVLopMonHoc;
import irepository.ISinhVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LopMonHoc;
import model.SVLopMonHoc;
import model.SinhVien;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class SVLopMonHocRepository implements ISVLopMonHoc {

    @Override
    public SVLopMonHoc getByID(int idSV, int idLop) {
        SVLopMonHoc svLop = null;
        try {
            String sql = "SELECT * FROM tblSVLopMonHoc WHERE idSv = ? AND idLop = ?";
            Connection connect = ConnectDB.getConnect();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idSV);
            stmt.setInt(2, idLop);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("idSv");
                double diemCC = rs.getDouble("diemChuyenCan");
                double diemGiuaKi = rs.getDouble("diemGiuaKi");
                double diemThi = rs.getDouble("diemThi");
                ILopMonHoc lopMonHoc = new LopMonHocRepository();
                LopMonHoc lmh = lopMonHoc.getByID(idLop);
                ISinhVien sinhVienRepo = new SinhVienRepository();
                SinhVien sv = sinhVienRepo.getByID(id);
                svLop = new SVLopMonHoc(sv, lmh, diemCC, diemGiuaKi, diemThi, diemGiuaKi);

            }
            stmt.close();
            rs.close();
            return svLop;
        } catch (SQLException ex) {

        }
        return svLop;
    }

    @Override
    public boolean update(SVLopMonHoc svLopMonHoc) {
        try {
            String sql = "UPDATE tblSVLopMonHoc SET diemChuyenCan = ?, diemGiuaKi = ?, diemThi = ? WHERE ";
            Connection connect = ConnectDB.getConnect();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setDouble(1, svLopMonHoc.getDiemCC());
            stmt.setDouble(2, svLopMonHoc.getDiemGiuaKi());
            stmt.setDouble(3, svLopMonHoc.getDiemThi());
            return stmt.execute();

        } catch (SQLException ex) {

        }
        return false;
    }

    @Override
    public List<SVLopMonHoc> getSVLopMonHoc(int idLopMonHoc) {
        List<SVLopMonHoc> sVLopMonHocs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblSVLopMonHoc WHERE idLop = ?";
            Connection connect = ConnectDB.getConnect();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idLopMonHoc);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idSv");
                double diemCC = rs.getDouble("diemChuyenCan");
                double diemGiuaKi = rs.getDouble("diemGiuaKi");
                double diemThi = rs.getDouble("diemThi");
                ILopMonHoc lopMonHoc = new LopMonHocRepository();
                LopMonHoc lmh = lopMonHoc.getByID(idLopMonHoc);
                ISinhVien sinhVienRepo = new SinhVienRepository();
                SinhVien sv = sinhVienRepo.getByID(id);
                SVLopMonHoc sVLopMonHoc = new SVLopMonHoc(sv, lmh, diemCC, diemGiuaKi, diemThi, diemGiuaKi);
                sVLopMonHocs.add(sVLopMonHoc);
            }
            stmt.close();
            rs.close();
            return sVLopMonHocs;
        } catch (SQLException ex) {

        }
        return sVLopMonHocs;
    }

}
