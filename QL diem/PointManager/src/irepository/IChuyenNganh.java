/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.ChuyenNganh;

/**
 *
 * @author vanph
 */
public interface IChuyenNganh {
    List<ChuyenNganh> getAll();
    ChuyenNganh getByID(int idChuyenNganh);
    boolean addChuyeNganh(ChuyenNganh chuyenNganh);
    boolean delete(int idChuyenNganh);
}
