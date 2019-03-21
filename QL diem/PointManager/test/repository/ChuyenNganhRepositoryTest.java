/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import model.ChuyenNganh;
import org.junit.Test;

/**
 *
 * @author vanph
 */
public class ChuyenNganhRepositoryTest extends TestCase {
    /**
     * Test of getAll method, of class ChuyenNganhRepository.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ChuyenNganhRepository instance = new ChuyenNganhRepository();
        List<ChuyenNganh> expResult = new ArrayList<>();
        List<ChuyenNganh> result = new ArrayList<>();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getByID method, of class ChuyenNganhRepository.
     */
    public void testGetByID() {
        System.out.println("getByID");
        int idChuyenNganh = 0;
        ChuyenNganhRepository instance = new ChuyenNganhRepository();
        ChuyenNganh expResult = null;
        ChuyenNganh result = instance.getByID(idChuyenNganh);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addChuyeNganh method, of class ChuyenNganhRepository.
     */
    public void testAddChuyeNganh() {
        System.out.println("addChuyeNganh");
        ChuyenNganh chuyenNganh = null;
        ChuyenNganhRepository instance = new ChuyenNganhRepository();
        boolean expResult = false;
        boolean result = instance.addChuyeNganh(chuyenNganh);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ChuyenNganhRepository.
     */
    public void testDelete() {
        System.out.println("delete");
        int idChuyenNganh = 0;
        ChuyenNganhRepository instance = new ChuyenNganhRepository();
        boolean expResult = false;
        boolean result = instance.delete(idChuyenNganh);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   
}
