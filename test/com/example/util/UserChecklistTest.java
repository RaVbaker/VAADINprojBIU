/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

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
public class UserChecklistTest {
    private UserChecklist instance;
    
    public UserChecklistTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        instance = new UserChecklist("John");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserName method, of class UserChecklist.
     */
    @Test
    public void testGetUserName() {
        System.out.println("getUserName");
        String expResult = "John Malkovich";
        UserChecklist localInstance = new UserChecklist(expResult);
        
        String result = localInstance.getUserName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of check method, of class UserChecklist.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        String movieName = "Być jak John Malkovich";
        int rating = 9;
        
        checkMovie(movieName, rating);
        
        assertTrue(instance.hasMovie(movieName));
    }
    
    private void checkMovie(String movieName, int rating) {
        instance.check(movieName, rating);
    }

    /**
     * Test of getCheckedMovieNames method, of class UserChecklist.
     */
    @Test
    public void testGetCheckedMovieNames() {
        System.out.println("getCheckedMovieNames");
        String movieName = "Być jak John Malkovich";
        int rating = 9;
        checkMovie(movieName, rating);
        String[] expResult = {movieName};
        String[] result = instance.getCheckedMovieNames();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getRatingForMovie method, of class UserChecklist.
     */
    @Test
    public void testGetRatingForMovie() {
        System.out.println("getRatingForMovie");
        String movieName = "Być jak John Malkovich";
        Integer rating = 9;
        
        checkMovie(movieName, rating);
        
        assertEquals(instance.getRatingForMovie(movieName), rating);
    }
    
    @Test
    public void testUncheck(){
        System.out.println("uncheck");
        
        String movieName = "Być jak John Malkovich";
        Integer rating = 9;
        
        checkMovie(movieName, rating);
        
        instance.uncheck(movieName);
        assertFalse(instance.hasMovie(movieName));
    }
}
