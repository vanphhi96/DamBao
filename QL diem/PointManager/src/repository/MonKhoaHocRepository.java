/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IChuyenNganh;
import irepository.IMonKhoaHoc;
import irepository.IMonNganh;
import irepository.INienKhoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ChuyenNganh;
import model.MonChuyenNganh;
import model.MonHoc;
import model.MonKhoaHoc;
import model.NienKhoa;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class MonKhoaHocRepository implements IMonKhoaHoc {

    @Override
    public List<MonHoc> getMonKhoa(int idNienKhoa) {
        List<MonHoc> monHocs = new ArrayList<>();
        try {
            String sql = "SELECT tblMonHoc.id, tblMonHoc.tenMonHoc, tblMonHoc.soTinChi, tblMonHoc.moTa "
                    + "FROM tblMonHoc, tblMonNganh, tblMonKhoaHoc"
                    + " WHERE tblMonKhoaHoc.idNienKHoa = ? "
                    + "AND tblMonKhoaHoc.idMonChuyenNganh = tblMonNganh.id "
                    + "AND tblMonNganh.idMon = tblMonHoc.id";
            Connection connect = ConnectDB.getConnect();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idNienKhoa);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tenMonHoc = rs.getString("tenMonHoc");
                int soTinChi = rs.getInt("soTinChi");
                String moTa = rs.getString("moTa");
                MonHoc monHoc = new MonHoc(id, tenMonHoc, soTinChi, moTa);
                monHocs.add(monHoc);
            }
            return monHocs;
        } catch (SQLException ex) {

        }
        return monHocs;
    }

    @Override
    public MonKhoaHoc getMonKhoaHoc(int idMon, int idNienKhoa) {
        MonKhoaHoc monKhoaHoc = null;
        try {
            String sql = "SELECT * FROM tblMonKhoaHoc WHERE idMonChuyenNganh = ? AND idNienKhoa = ?";
            Connection connect = ConnectDB.getConnect();
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idMon);
            stmt.setInt(2, idNienKhoa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                IMonNganh monNganhRepo = new MonNganhRepository();
                INienKhoa nienKhoaRepo = new NienKhoaRepository();
                MonChuyenNganh monNganh = monNganhRepo.getByID(idMon);
                NienKhoa nienKhoa = nienKhoaRepo.getByID(idNienKhoa);
                monKhoaHoc = new MonKhoaHoc(id, monNganh, nienKhoa);
            }
            return monKhoaHoc;
        } catch (SQLException ex) {

        }
        return monKhoaHoc;
    }

    @Override
    public MonKhoaHoc getByID(int idMonKhoaHoc) {
        MonKhoaHoc monKhoaHoc = null;
        try {
            String sql = "SELECT * FROM tblChuyenNganh";
            Connection connect = ConnectDB.getConnect();
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                int idMonNganh = rs.getInt("idMonChuyenNganh");
                int idNienKhoa = rs.getInt("idNienKhoa");
                IMonNganh monNganhRepo = new MonNganhRepository();
                INienKhoa nienKhoaRepo = new NienKhoaRepository();
                MonChuyenNganh monNganh = monNganhRepo.getByID(idMonNganh);
                NienKhoa nienKhoa = nienKhoaRepo.getByID(idNienKhoa);
                monKhoaHoc = new MonKhoaHoc(idMonKhoaHoc, monNganh, nienKhoa);

            }
            return monKhoaHoc;
        } catch (SQLException ex) {

        }
        return monKhoaHoc;
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
