/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.ILopMonHoc;
import irepository.IMonKhoaHoc;
import java.util.List;
import junit.framework.TestCase;
import model.LopMonHoc;
import model.MonKhoaHoc;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanph
 */
public class LopMonHocTest extends TestCase{
  
    /**
     * truong hop id khong ton tai
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        int idLopMonHoc = 0;
        ILopMonHoc instance = new LopMonHocRepository();
        LopMonHoc expResult = null;
        LopMonHoc result = instance.getByID(idLopMonHoc);
        assertEquals(expResult, result);
    }
    
     /**
     * truong hop id ton tai
     */
    @Test
    public void testGetByID2() {
        System.out.println("getByID");
        int idLopMonHoc = 3;
        ILopMonHoc instance = new LopMonHocRepository();
        IMonKhoaHoc monKhoaHocRepo = new MonKhoaHocRepository();
        int idMonKhoaHocResult = 2;
        MonKhoaHoc monKhoaHoc = monKhoaHocRepo.getByID(idMonKhoaHocResult);
        LopMonHoc expResult = new LopMonHoc(3,monKhoaHoc,60,"101","D14-011");
        LopMonHoc result = instance.getByID(idLopMonHoc);
        assertEquals(expResult.getPhongHoc(), result.getPhongHoc());
        assertEquals(expResult.getTenLop(), result.getTenLop());
        assertEquals(expResult.getSoLuong(), result.getSoLuong());
    }

    /**
     * Test of getLopMonHocs method, of class LopMonHocRepository.
     */
    @Test
    public void testGetLopMonHocs() {
        System.out.println("getLopMonHocs");
        MonKhoaHoc monKhoaHoc = new MonKhoaHoc();
        int idMonKhoaHoc = 3;// get in database
        monKhoaHoc.setIdMonKhoaHoc(idMonKhoaHoc);
        LopMonHocRepository instance = new LopMonHocRepository();
        int sizeResult = 2;
        List<LopMonHoc> result = instance.getLopMonHocs(monKhoaHoc);
        assertEquals(sizeResult, result.size());
    }
    
     /**
     * Test of getLopMonHocs method, of class LopMonHocRepository.
     */
    @Test
    public void testGetLopMonHocs2() {
        System.out.println("getLopMonHocs 2");
        MonKhoaHoc monKhoaHoc = new MonKhoaHoc();
        int idMonKhoaHoc = 0;// get in database
        monKhoaHoc.setIdMonKhoaHoc(idMonKhoaHoc);
        LopMonHocRepository instance = new LopMonHocRepository();
        int sizeResult = 0;
        List<LopMonHoc> result = instance.getLopMonHocs(monKhoaHoc);
        assertEquals(sizeResult, result.size());
    }
    
}
