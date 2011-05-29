/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ravbaker
 */
public class UserChecklist {

    private final String userName;
    private Map checklist = new HashMap<String, Integer>();

    public UserChecklist(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void check(String movieName, int rating) {
        checklist.put(movieName, rating);
    }
    
    public void uncheck(String movieName) {
        checklist.remove(movieName);
    }

    public String[] getCheckedMovieNames() {
        Set set = checklist.keySet();
        return (String[]) set.toArray(new String[set.size()]);
    }

    public Integer getRatingForMovie(String movieName) {
        return (Integer)checklist.get(movieName);
    }
    
    public boolean hasMovie(String movieName){
        return checklist.containsKey(movieName);
    }
    
    public Iterator iterator() {
        return checklist.entrySet().iterator();
    }
}
