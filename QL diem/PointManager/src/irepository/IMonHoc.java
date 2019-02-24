/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package irepository;

import java.util.List;
import model.MonHoc;

/**
 *
 * @author vanph
 */
public interface IMonHoc {
    MonHoc getMHByID(int idMonHoc);
    List<MonHoc> getAll();
    boolean addMonHoc(MonHoc monHoc);
    boolean delete(int idMonHoc);
}
