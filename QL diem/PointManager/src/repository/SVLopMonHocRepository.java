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
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                String ghiChu = rs.getString("ghiChu");
                ILopMonHoc lopMonHoc = new LopMonHocRepository();
                LopMonHoc lmh = lopMonHoc.getByID(idLop);
                ISinhVien sinhVienRepo = new SinhVienRepository();
                SinhVien sv = sinhVienRepo.getByID(id);
                svLop = new SVLopMonHoc(sv, lmh, diemCC, diemGiuaKi, diemThi, ghiChu);

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
        if (svLopMonHoc.getDiemCC() < 0 || svLopMonHoc.getDiemCC() > 10) {
            return false;
        }
        if (svLopMonHoc.getDiemGiuaKi() < 0 || svLopMonHoc.getDiemGiuaKi() > 10) {
            return false;
        }
        if (svLopMonHoc.getDiemThi() < 0 || svLopMonHoc.getDiemThi() > 10) {
            return false;
        }
        Connection connect = ConnectDB.getConnect();
        try {
            connect.setAutoCommit(false);
            String sql = "UPDATE tblSVLopMonHoc SET diemChuyenCan = ?, diemGiuaKi = ?, diemThi = ?, ghiChu = ? WHERE idSv = ? AND idLop = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setDouble(1, svLopMonHoc.getDiemCC());
            stmt.setDouble(2, svLopMonHoc.getDiemGiuaKi());
            stmt.setDouble(3, svLopMonHoc.getDiemThi());
            stmt.setString(4, svLopMonHoc.getGhiChu());
            stmt.setInt(5, svLopMonHoc.getSinhVien().getId());
            stmt.setInt(6, svLopMonHoc.getLop().getIdLop());
            stmt.executeUpdate();
            stmt.close();
            connect.commit();
            System.out.println("update success!");
            return true;

        } catch (SQLException ex) {
            try {
                connect.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(SVLopMonHocRepository.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("rollback");
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
                String ghiChu = rs.getString("ghiChu");
                ILopMonHoc lopMonHoc = new LopMonHocRepository();
                LopMonHoc lmh = lopMonHoc.getByID(idLopMonHoc);
                ISinhVien sinhVienRepo = new SinhVienRepository();
                SinhVien sv = sinhVienRepo.getByID(id);
                SVLopMonHoc sVLopMonHoc = new SVLopMonHoc(sv, lmh, diemCC, diemGiuaKi, diemThi, ghiChu);
                sVLopMonHocs.add(sVLopMonHoc);
            }
            stmt.close();
            rs.close();
            return sVLopMonHocs;
        } catch (SQLException ex) {

        }
        return sVLopMonHocs;
    }

    @Override
    public boolean addSvLopMonHoc(SVLopMonHoc svLopMonHoc) {
        Connection connect = ConnectDB.getConnect();
        try {
            connect.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(SVLopMonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "INSERT INTO tblSVLopMonHoc (idSv, idLop, diemChuyenCan,diemGiuaKi,diemThi, ghiChu) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, svLopMonHoc.getSinhVien().getId());
            stmt.setInt(2, svLopMonHoc.getLop().getIdLop());
            stmt.setDouble(3, svLopMonHoc.getDiemCC());
            stmt.setDouble(4, svLopMonHoc.getDiemGiuaKi());
            stmt.setDouble(5, svLopMonHoc.getDiemThi());
            stmt.setString(6, svLopMonHoc.getGhiChu());
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
    public boolean delete(SVLopMonHoc sVLopMonHoc) {
        Connection connect = ConnectDB.getConnect();
        try {
            connect.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(SVLopMonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "DELETE tblSvLopMonHoc WHERE idSv = ? AND idLop = ?";
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, sVLopMonHoc.getSinhVien().getId());
            stmt.setInt(2, sVLopMonHoc.getLop().getIdLop());
            stmt.execute();
            connect.commit();
            System.out.println("commit");
            return true;

        } catch (SQLException ex) {
            try {
                connect.rollback();
                return false;
            } catch (SQLException ex1) {
                Logger.getLogger(SVLopMonHocRepository.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("rollback");
        }
        return false;
    }

}
