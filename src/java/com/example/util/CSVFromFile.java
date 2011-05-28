
package com.example.util;

import com.example.vendor.CSV;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ravbaker
 */
public class CSVFromFile {

    private static final char DEFAULT_DELIMITER = ',';
        
    private String fileName = null;
    private  char delimiter;
    private List parsedCSV = null;
    
    public CSVFromFile(String fileName, char delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
        readFile();
    }

    public CSVFromFile(String fileName) {
        this(fileName, DEFAULT_DELIMITER);
    }
    
    public List getCSV() {
        return parsedCSV;
    }

    private void readFile() {
        clearParsedCSV();
        try {
            tryReadFile();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void clearParsedCSV() {
        parsedCSV = new ArrayList();
    }

    private void tryReadFile() throws FileNotFoundException, IOException {
        // Open the file that is the first 
        // command line parameter
        FileInputStream fstream = new FileInputStream(fileName);
        // Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            
            appendCSVLine(strLine);
        }
        //Close the input stream
        in.close();
    }

    private void appendCSVLine(String strLine) {
        List lineAsCSVFields = parseLineAsCSV(strLine);
        parsedCSV.add(lineAsCSVFields);
    }
       
    private List parseLineAsCSV(String strLine) {
        CSV csvParser = new CSV(delimiter);
        
        return csvParser.parse(strLine);
    }
}
