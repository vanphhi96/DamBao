/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IChuyenNganh;
import irepository.IMonHoc;
import irepository.IMonNganh;
import java.util.List;
import junit.framework.TestCase;
import model.ChuyenNganh;
import model.MonChuyenNganh;
import model.MonHoc;
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
public class MonChuyenNganhTest extends TestCase{

    /**
     * Test of getMonChuyenNganh method, of class MonNganhRepository.
     */
    @Test
    public void testGetMonChuyenNganh() {
        System.out.println("getMonChuyenNganh");
        IMonHoc monHocRepo = new MonHocRepository();
        IChuyenNganh monNganhRepo = new ChuyenNganhRepository();
        int idMonHoc = 208;
        int idChuyenNganh = 1;
        MonHoc monHoc = monHocRepo.getMHByID(idMonHoc);
        ChuyenNganh chuyenNganh = monNganhRepo.getByID(idChuyenNganh);
        IMonNganh instance = new MonNganhRepository();
        MonChuyenNganh expResult = new MonChuyenNganh(3);
        MonChuyenNganh result = instance.getMonChuyenNganh(monHoc, chuyenNganh);
        assertEquals(expResult.getId(), result.getId());
    }
    
     /**
     * Test of getMonChuyenNganh method, of class MonNganhRepository.
     */
    @Test
    public void testGetMonChuyenNganh2() {
        System.out.println("getMonChuyenNganh2");
        IMonHoc monHocRepo = new MonHocRepository();
        IChuyenNganh monNganhRepo = new ChuyenNganhRepository();
        int idMonHoc = 208;
        int idChuyenNganh = 2;
        MonHoc monHoc = monHocRepo.getMHByID(idMonHoc);
        ChuyenNganh chuyenNganh = monNganhRepo.getByID(idChuyenNganh);
        IMonNganh instance = new MonNganhRepository();
        MonChuyenNganh expResult = null;
        MonChuyenNganh result = instance.getMonChuyenNganh(monHoc, chuyenNganh);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMonNganhs method, of class MonNganhRepository.
     */
    @Test
    public void testGetMonNganhs() {
        System.out.println("getMonNganhs");
        int idNganh = 1;
         int sizeResult = 5;
        IMonNganh instance = new MonNganhRepository();
        List<MonHoc> result = instance.getMonNganhs(idNganh);
        System.out.println("size "+result.size());
        assertEquals(sizeResult, result.size());
    }
    
     /**
     * Test of getMonNganhs method, of class MonNganhRepository.
     */
    @Test
    public void testGetMonNganhs2() {
        System.out.println("getMonNganhs2");
        int idNganh = 3;
        int sizeResult = 0;
        MonNganhRepository instance = new MonNganhRepository();
        List<MonHoc> result = instance.getMonNganhs(idNganh);
        assertEquals(sizeResult, result.size());
    }
    
}
