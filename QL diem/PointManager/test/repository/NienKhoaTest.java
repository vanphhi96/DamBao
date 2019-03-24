/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import irepository.INienKhoa;
import java.util.List;
import junit.framework.TestCase;
import model.NienKhoa;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vanph
 */
public class NienKhoaTest extends TestCase  {

    /**
     * Test of getByID method, of class NienKhoaRepository.
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        int idNienKhoa = 1;
        INienKhoa instance = new NienKhoaRepository();
        NienKhoa result = new NienKhoa(1, "D14", "");
        NienKhoa expResult = instance.getByID(idNienKhoa);
        assertEquals(expResult.getId(), idNienKhoa);
    }

    /**
     * Test of getNienKhoas method, of class NienKhoaRepository.
     */
    @Test
    public void testGetNienKhoas() {
        System.out.println("getNienKhoas");
        int idChuyenNganh = 1;
        INienKhoa instance = new NienKhoaRepository();
        List<NienKhoa> result = instance.getNienKhoas(idChuyenNganh);
         assertEquals(result.size(), 3);
    }
    
     /**
     * Test of getNienKhoas method, of class NienKhoaRepository.
     */
    @Test
    public void testGetNienKhoas2() {
        System.out.println("getNienKhoas");
        int idChuyenNganh = 100;
        INienKhoa instance = new NienKhoaRepository();
        List<NienKhoa> result = instance.getNienKhoas(idChuyenNganh);
         assertEquals(result.size(), 0);
    }


}
