/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IMonHoc;
import java.util.List;
import junit.framework.TestCase;
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
public class MonHocTest extends TestCase {

    /**
     * Test of getMHByID method, of class MonHocRepository.
     */
    @Test
    public void testGetMHByID() {
        System.out.println("getMHByID");
        int idMonHoc = 208;
        IMonHoc instance = new MonHocRepository();
        MonHoc expResult = new MonHoc(208, "Đại số", 3, "");
        MonHoc result = instance.getMHByID(idMonHoc);
        assertEquals(expResult.getTenMonHoc(), result.getTenMonHoc());
        assertEquals(expResult.getId(), result.getId());
    }

    /**
     * Test of getAll method, of class MonHocRepository.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        MonHocRepository instance = new MonHocRepository();
        List<MonHoc> result = instance.getAll();
        assertEquals(result.size(), 14);
        int startId = 208;
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i).getId(), startId);
            startId++;
        }
    }

}
