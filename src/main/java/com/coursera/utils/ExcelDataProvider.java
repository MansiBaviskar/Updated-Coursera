package com.coursera.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelDataProvider {
    
    public static void createExcelFile() {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("TestData");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"TestCase", "SearchTerm", "FirstName", "LastName", "Email", "Phone", "OrgType", "Company", "EmpRange", "Country", "Title"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            
            // Create data rows
            String[][] data = {
                {"TC001", "Web Development", "Mansi", "Baviskar", "baviskarmansigmail.com", "9876543234", "Business", "xyz", "501-1000", "India", "xyz"},
                {"TC002", "Java Programming", "John", "Doe", "invalid-email", "1234567890", "Business", "ABC Corp", "1-500", "India", "Manager"},
                {"TC003", "Python Programming", "Jane", "Smith", "test@invalid", "9876543210", "Business", "XYZ Edu", "1001-5000", "India", "Developer"}
            };
            
            for (int i = 0; i < data.length; i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(data[i][j]);
                }
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/testdata/CourseraData.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            
            System.out.println("Excel file created successfully!");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getCellData(String fileName, String testCase, String columnName) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/" + fileName + ".xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
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
                    workbook.close();
                    fis.close();
                    return row.getCell(columnIndex).getStringCellValue();
                }
            }
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static Map<String, String> getTestData(String fileName, String testCase) {
        Map<String, String> data = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/" + fileName + ".xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
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
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static Object[][] getAllTestData(String fileName) {
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata/" + fileName + ".xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            
            int rowCount = sheet.getLastRowNum();
            data = new Object[rowCount][1];
            
            for (int i = 1; i <= rowCount; i++) {
                data[i-1][0] = sheet.getRow(i).getCell(0).getStringCellValue();
            }
            
            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}