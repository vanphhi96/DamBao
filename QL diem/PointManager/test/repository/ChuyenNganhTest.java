/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.IChuyenNganh;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import model.ChuyenNganh;
import org.junit.Test;

/**
 *
 * @author vanph
 */
public class ChuyenNganhTest extends TestCase {

    /**
     * Test of getAll method, of class ChuyenNganhRepository.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        IChuyenNganh instance = new ChuyenNganhRepository();
        List<ChuyenNganh> result = instance.getAll();
        List<ChuyenNganh> expResult = new ArrayList<>();
        expResult.add(new ChuyenNganh(1, "CNTT", null));
        expResult.add(new ChuyenNganh(2, "Vien Thong", null));
        expResult.add(new ChuyenNganh(3, "Da Phuong Tien", null));
        expResult.add(new ChuyenNganh(4, "Ke Toan", null));
        assertEquals(expResult.size(), result.size());
        for (int i = 0; i < result.size(); i++) {
            assertEquals(result.get(i).getId(), result.get(i).getId());
        }

    }
}
