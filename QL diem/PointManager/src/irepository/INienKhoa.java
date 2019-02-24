/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.NienKhoa;

/**
 *
 * @author vanph
 */
public interface INienKhoa {
    NienKhoa getByID(int idNienKhoa);
    List<NienKhoa> getNienKhoas(int idChuyenNganh);
    List<NienKhoa> getAll();
}
