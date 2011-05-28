/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.server;

import com.example.util.CSVFromFile;
import com.example.util.IMDBChartRecord;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ravbaker
 */
public class IMDBChartList {
    
    private final static String DEFAULT_LIST_FILENAME = "/Users/ravbaker/Desktop/top250.csv";
    
    private String listFileName = null;
    private List csvData = null;
    
    private Map chartList = new HashMap<String, IMDBChartRecord>();
    
    public IMDBChartList(String listFileName) {
        this.listFileName = listFileName;
        loadCSV();
        loadChartListFromCSV();
    }
    
    public IMDBChartList() {
        this(DEFAULT_LIST_FILENAME);
    }
    
    private void loadCSV() {
       CSVFromFile csvExtractor = new CSVFromFile(listFileName, '\t'); 
       
       csvData = csvExtractor.getCSV();
    }

    private void loadChartListFromCSV() {
        Iterator csvDataIterator = csvData.iterator();
        
        while(csvDataIterator.hasNext()) {
            List csvRecord = (List)csvDataIterator.next();
            appendListToChartListMap(csvRecord);
            
        }
    }

    private void appendListToChartListMap(List csvRecord) {
        IMDBChartRecord record = new IMDBChartRecord(csvRecord);
        chartList.put(record.getTitle(), record);
    }
    
    public IMDBChartRecord getMovie(String movieName) {
        return (IMDBChartRecord)chartList.get(movieName);
    }
    
    public HashMap<String,IMDBChartRecord> getAllMovies() {
        return (HashMap<String, IMDBChartRecord>) chartList;
    }
    
    public Iterator iterator() {
        return chartList.entrySet().iterator();
    }
    
    
}
