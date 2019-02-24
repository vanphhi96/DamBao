/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IChuyenNganh;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChuyenNganh;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class ChuyenNganhRepository implements IChuyenNganh {

    @Override
    public List<ChuyenNganh> getAll() {
        List<ChuyenNganh> chuyenNganhs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tblChuyenNganh";
            Connection connect = ConnectDB.getConnect();
            Statement statement = connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("tenChuyenNganh");
                String mota = rs.getString("description");
                ChuyenNganh chuyenNganh = new ChuyenNganh(id, ten, mota);
                chuyenNganhs.add(chuyenNganh);
            }
            return chuyenNganhs;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenNganhRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chuyenNganhs;
    }

    @Override
    public ChuyenNganh getByID(int idChuyenNganh) {
        ChuyenNganh chuyenNganh = null;
        String sql = "SELECT * FROM tblChuyenNganh WHERE id = ?";
        Connection connect = ConnectDB.getConnect();
        try {
            PreparedStatement stmt = connect.prepareStatement(sql);
            stmt.setInt(1, idChuyenNganh);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                 chuyenNganh = new ChuyenNganh(idChuyenNganh, rs.getString("tenChuyenNganh"), rs.getString("description"));
                 System.out.println("yes");
            }
            else{
                System.out.println("No");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MonHocRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chuyenNganh;
    }

    @Override
    public boolean addChuyeNganh(ChuyenNganh chuyenNganh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int idChuyenNganh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
