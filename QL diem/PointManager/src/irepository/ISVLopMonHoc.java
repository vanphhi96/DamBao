/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.SVLopMonHoc;

/**
 *
 * @author vanph
 */
public interface ISVLopMonHoc {
    SVLopMonHoc getByID(int idSVLopMonHoc, int idLop);
    boolean update(SVLopMonHoc svLopMonHoc);
    List<SVLopMonHoc> getSVLopMonHoc(int idLopMonHoc);
    boolean addSvLopMonHoc(SVLopMonHoc svLopMonHoc);
    boolean delete(SVLopMonHoc sVLopMonHoc);
    List<SVLopMonHoc> getSVLopMonHocByIDSV(int idSV);
}
