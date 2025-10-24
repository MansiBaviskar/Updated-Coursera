package com.coursera.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtils {
    
    public static String getCellData(String fileName, String testCase, String columnName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/testdata/" + fileName + ".csv"));
            String line;
            String[] headers = null;
            
            if ((line = br.readLine()) != null) {
                headers = line.split(",");
            }
            
            int columnIndex = -1;
            for (int i = 0; i < headers.length; i++) {
                if (headers[i].equals(columnName)) {
                    columnIndex = i;
                    break;
                }
            }
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(testCase)) {
                    br.close();
                    return values[columnIndex];
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static Map<String, String> getTestData(String fileName, String testCase) {
        Map<String, String> data = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/testdata/" + fileName + ".csv"));
            String line;
            String[] headers = null;
            
            if ((line = br.readLine()) != null) {
                headers = line.split(",");
            }
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(testCase)) {
                    for (int i = 0; i < headers.length; i++) {
                        data.put(headers[i], values[i]);
                    }
                    break;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static Object[][] getAllTestData(String fileName) {
        List<String[]> testCases = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/testdata/" + fileName + ".csv"));
            String line;
            
            br.readLine(); // Skip header
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                testCases.add(new String[]{values[0]});
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Object[][] data = new Object[testCases.size()][1];
        for (int i = 0; i < testCases.size(); i++) {
            data[i] = testCases.get(i);
        }
        return data;
    }
}