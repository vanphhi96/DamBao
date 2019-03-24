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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import model.LopMonHoc;
import model.SVLopMonHoc;
import model.SinhVien;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.ConnectDB;

/**
 *
 * @author vanph
 */
public class SVLopMonHocTest extends TestCase {

    /**
     * Test khong co du lieu
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        int idSV = 0;
        int idLop = 0;
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        SVLopMonHoc expResult = null;
        SVLopMonHoc result = instance.getByID(idSV, idLop);
        assertEquals(expResult, result);
    }

    /**
     * Test co du lieu
     */
    @Test
    public void testGetByID2() {
        System.out.println("getByID");
        int idSV = 4;
        int idLop = 3;
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        ILopMonHoc lopMonHoc = new LopMonHocRepository();
        LopMonHoc lmh = lopMonHoc.getByID(idLop);
        ISinhVien sinhVienRepo = new SinhVienRepository();
        SinhVien sv = sinhVienRepo.getByID(idSV);
        SVLopMonHoc expResult = new SVLopMonHoc();
        expResult.setSinhVien(sv);
        expResult.setLop(lmh);
        SVLopMonHoc result = instance.getByID(idSV, idLop);
        assertEquals(expResult.getSinhVien().getTenSV(), result.getSinhVien().getTenSV());
        assertEquals(expResult.getLop().getTenLop(), result.getLop().getTenLop());
    }

    /**
     * Test update success
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        int idSV = 4;
        int idLop = 3;
        SVLopMonHoc svLopMonHoc = instance.getByID(idSV, idLop);
        SVLopMonHoc svLopMonHocTemp = instance.getByID(idSV, idLop);
        double diemCCUpdate = 6;
        double diemGiuaKiUpdate = 7;
        svLopMonHoc.setDiemCC(diemCCUpdate);
        svLopMonHoc.setDiemGiuaKi(diemGiuaKiUpdate);
        boolean expResult = true;
        boolean result = instance.update(svLopMonHoc);
        assertEquals(expResult, result);
        svLopMonHoc = instance.getByID(idSV, idLop);
        assertEquals(svLopMonHoc.getDiemCC(), diemCCUpdate);
        assertEquals(svLopMonHoc.getDiemGiuaKi(), diemGiuaKiUpdate);

        instance.update(svLopMonHocTemp);//update lai du lieu ban dau
    }
    
     /**
     * Test update fail
     */
    @Test
    public void testUpdate2() {
        System.out.println("update");
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        int idSV = 4;
        int idLop = 3;
        SVLopMonHoc svLopMonHoc = instance.getByID(idSV, idLop);
        double diemCCUpdate = 11;
        double diemGiuaKiUpdate = 12;
        svLopMonHoc.setDiemCC(diemCCUpdate);
        svLopMonHoc.setDiemGiuaKi(diemGiuaKiUpdate);
        boolean expResult = false;
        boolean result = instance.update(svLopMonHoc);
        assertEquals(expResult, result);
     
    }


    /**
     * Test idLop khong co sinh vien
     */
    @Test
    public void testGetSVLopMonHoc() {
        System.out.println("getSVLopMonHoc");
        int idLopMonHoc = 0;
        int sizeResult = 0;
        SVLopMonHocRepository instance = new SVLopMonHocRepository();
        List<SVLopMonHoc> result = instance.getSVLopMonHoc(idLopMonHoc);
        assertEquals(sizeResult, result.size());

    }

    /**
     * Test idLop co sinh vien
     */
    @Test
    public void testGetSVLopMonHoc2() {
        System.out.println("getSVLopMonHoc");
        int idLopMonHoc = 0;
        int sizeResult = 0;
        SVLopMonHocRepository instance = new SVLopMonHocRepository();
        List<SVLopMonHoc> result = instance.getSVLopMonHoc(idLopMonHoc);
        assertEquals(sizeResult, result.size());

    }

    /**
     * Add success
     */
    @Test
    public void testAddSvLopMonHoc() {
        System.out.println("addSvLopMonHoc");
        SVLopMonHoc svLopMonHoc = null;
        int idSv = 21;
        int idLop = 3;
        ISinhVien iSinhVien = new SinhVienRepository();
        SinhVien sv = iSinhVien.getByID(idSv);
        ILopMonHoc iLop = new LopMonHocRepository();
        LopMonHoc lopMonHoc = iLop.getByID(idLop);
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        svLopMonHoc = new SVLopMonHoc(sv, lopMonHoc, 0, 0, 0, "");
        boolean expResult = true;
        boolean result = instance.addSvLopMonHoc(svLopMonHoc);
        assertEquals(expResult, result);
        svLopMonHoc = instance.getByID(idSv, idLop);
        instance.delete(svLopMonHoc);// khoi phuc lai du lieu ban dau

    }

    /**
     * Add fails
     */
    @Test
    public void testAddSvLopMonHoc2() {
        System.out.println("addSvLopMonHoc 2");
        SVLopMonHoc svLopMonHoc = null;
        int idSv = 21;
        int idLop = 11;
        ISinhVien iSinhVien = new SinhVienRepository();
        SinhVien sv = iSinhVien.getByID(idSv);
        LopMonHoc lopMonHoc = new LopMonHoc();
        lopMonHoc.setIdLop(idLop);
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        svLopMonHoc = new SVLopMonHoc(sv, lopMonHoc, 0, 0, 0, "");
        boolean expResult = false;
        boolean result = instance.addSvLopMonHoc(svLopMonHoc);
        assertEquals(expResult, result);

    }

    /**
     * Test of delete success
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        int idSv = 4;
        int idLop = 3;
        SVLopMonHoc sVLopMonHoc = null;
        ISVLopMonHoc instance = new SVLopMonHocRepository();
        sVLopMonHoc = instance.getByID(idSv, idLop);
        boolean expResult = true;
        boolean result = instance.delete(sVLopMonHoc);
        assertEquals(expResult, result);

        //khoi phuc lai du lieu
        instance.addSvLopMonHoc(sVLopMonHoc);

    }
}
