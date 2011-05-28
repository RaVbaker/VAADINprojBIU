/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.server;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import com.example.util.IMDBChartRecord;
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
public class IMDBChartListTest {
    
    private String filePath = "/Users/ravbaker/NetBeansProjects/vaadin-biu/test/com/example/files/";
    private IMDBChartList instance;
    
    public IMDBChartListTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        instance = new IMDBChartList(filePath+"test_chart.csv");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMovie method, of class IMDBChartList.
     */
    @Test
    public void testGetMovie() {
        System.out.println("getMovie");
        String movieName = "";
        
//3        9.0	Ojciec chrzestny II (1974)	281,735
        IMDBChartRecord expResult = new IMDBChartRecord(3,  9.0F, "Ojciec chrzestny II (1974)", "281,735");
        
        IMDBChartRecord result = instance.getMovie(expResult.getTitle());
        assertTrue(expResult.equals(result));
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testGetAllMovies() {
        System.out.println("getAllMovies");
        Map<String,IMDBChartRecord> allMovies = (HashMap<String, IMDBChartRecord>)allTestMoviesAsHashMap();
        Map<String,IMDBChartRecord> instanceAllMovies = instance.getAllMovies();
        
        assertEqualHashMaps(instanceAllMovies, allMovies);
    }
    
    
    private Map allTestMoviesAsHashMap() {
        Map<String,IMDBChartRecord> allMovies = new HashMap<String, IMDBChartRecord>();
        
        /* dane w CSV:
        1	9.2	Skazani na Shawshank (1994)	596,718
        2	9.2	Ojciec chrzestny (1972)	460,927
        3	9.0	Ojciec chrzestny II (1974)	281,735
         */
        allMovies.put("Skazani na Shawshank (1994)", new IMDBChartRecord(1,9.2F,"Skazani na Shawshank (1994)", "596,718" ));
        allMovies.put("Ojciec chrzestny (1972)", new IMDBChartRecord(2,9.2F,"Ojciec chrzestny (1972)", "460,927" ));
        allMovies.put("Ojciec chrzestny II (1974)", new IMDBChartRecord(3,9.0F,"Ojciec chrzestny II (1974)", "281,735" ));
        
        return allMovies;
    }
    
    private void assertEqualHashMaps(Map map, Map expectedMap) {
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry record = (Map.Entry)it.next(); 
            String movieName = (String)record.getKey();
            IMDBChartRecord chartRecord = (IMDBChartRecord)record.getValue();
            IMDBChartRecord chartListRecord = (IMDBChartRecord)expectedMap.get(movieName);
            System.out.println("Check hashmap key: "+movieName);
            assertTrue(chartRecord.equals(chartListRecord));
        }
    }

    
}
