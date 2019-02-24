/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.MonChuyenNganh;
import model.MonHoc;

/**
 *
 * @author vanph
 */
public interface IMonNganh {
    MonChuyenNganh getMonChuyenNganh(int idMon, int idChuyenNganh);
    MonChuyenNganh getByID(int id);
    List<MonHoc> getMonNganhs(int idNganh);
    boolean addMonNganh(MonChuyenNganh monChuyenNganh);
    boolean delete(int idMonNganh);
}
