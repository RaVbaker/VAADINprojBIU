/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import com.example.util.IMDBChartRecord;
import java.util.List;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ravbaker
 */
public class IMDBChartRecordTest {
    
    private static List data = Arrays.asList("3","3.5","movie name", "213,456");
    
    IMDBChartRecord instance;
    
    public IMDBChartRecordTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        instance = new IMDBChartRecord(data);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRank method, of class IMDBChartRecord.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        
        int expResult = 3;
        int result = instance.getRank();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getRating method, of class IMDBChartRecord.
     */
    @Test
    public void testGetRating() {
        System.out.println("getRating");
       
        float expResult = 3.5F;
        float result = instance.getRating();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getTitle method, of class IMDBChartRecord.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
       
        String expResult = "movie name";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of getVotes method, of class IMDBChartRecord.
     */
    @Test
    public void testGetVotes() {
        System.out.println("getVotes");
       
        String expResult = "213,456";
        String result = instance.getVotes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testToArray() {
        System.out.println("toArray");
        Object[] expected = new Object[]{ 3, 3.5f, "movie name", "213,456"};
        assertArrayEquals(expected, instance.toArray());
    }
}
