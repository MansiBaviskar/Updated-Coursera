package com.coursera.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    private static Workbook workbook;
    private static Sheet sheet;
    
    public static String getCellData(String fileName, String testCase, String columnName) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/" + fileName + ".xlsx");
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            
            Row headerRow = sheet.getRow(0);
            int columnIndex = -1;
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                if (headerRow.getCell(i).getStringCellValue().equals(columnName)) {
                    columnIndex = i;
                    break;
                }
            }
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().equals(testCase)) {
                    return row.getCell(columnIndex).getStringCellValue();
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static Map<String, String> getTestData(String fileName, String testCase) {
        Map<String, String> data = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/" + fileName + ".xlsx");
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            
            Row headerRow = sheet.getRow(0);
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row.getCell(0).getStringCellValue().equals(testCase)) {
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        String key = headerRow.getCell(j).getStringCellValue();
                        String value = row.getCell(j).getStringCellValue();
                        data.put(key, value);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static Object[][] getAllTestData(String fileName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/" + fileName + ".xlsx");
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            
            int rowCount = sheet.getLastRowNum();
            data = new Object[rowCount][1];
            
            for (int i = 1; i <= rowCount; i++) {
                data[i-1][0] = sheet.getRow(i).getCell(0).getStringCellValue();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}