/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.LopMonHoc;

/**
 *
 * @author vanph
 */
public interface ILopMonHoc {
    LopMonHoc getByID(int idLopMonHoc);
    List<LopMonHoc> getLopMonHocs(int idMonKhoaHoc);
    boolean addLopMonHoc(LopMonHoc lopMonHoc);
    boolean delete(int idLopMonHoc);
}
