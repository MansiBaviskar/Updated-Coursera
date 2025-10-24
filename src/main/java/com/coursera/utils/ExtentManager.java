package com.coursera.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    
    private static ExtentReports extent;
    private static ExtentTest test;
    
    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/" + fileName);
        sparkReporter.config().setReportName("Coursera Automation Report");
        sparkReporter.config().setDocumentTitle("Test Results");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
        
        return extent;
    }
    
    public static ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        return test;
    }
    
    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}