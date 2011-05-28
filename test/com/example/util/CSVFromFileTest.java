/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.util.Arrays;
import com.example.util.CSVFromFile;
import java.util.List;
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
public class CSVFromFileTest {
    
    private String filePath = "/Users/ravbaker/NetBeansProjects/vaadin-biu/test/com/example/files/";
    
    public CSVFromFileTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCSV method, of class CSVFromFile.
     */
    @Test
    public void testGetCSV() {
        System.out.println("getCSV");
        CSVFromFile instance = new CSVFromFile(filePath+"test.csv");
        List expResult = Arrays.asList(Arrays.asList("1","10.10","test movie", "123,456"));
        List result = instance.getCSV();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    @Test
    public void testGetCSVDelimitedByTab() {
        System.out.println("getCSV by tab");
        CSVFromFile instance = new CSVFromFile(filePath+"test_tab.csv",'\t');
        List expResult = Arrays.asList(Arrays.asList("1","10.10","test movie", "123,456"));
        List result = instance.getCSV();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
}
