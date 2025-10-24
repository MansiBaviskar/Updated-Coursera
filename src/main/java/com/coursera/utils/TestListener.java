package com.coursera.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Field;

public class TestListener implements ITestListener {
    
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Object testClass = result.getInstance();
            Field driverField = testClass.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            WebDriver driver = (WebDriver) driverField.get(testClass);
            
            if (driver != null) {
                String testName = result.getMethod().getMethodName();
                ScreenshotUtil.captureScreenshot(driver, testName + "_FAILED");
            }
        } catch (Exception e) {
            LoggerUtil.error("Failed to capture screenshot on test failure: " + e.getMessage());
        }
    }
}