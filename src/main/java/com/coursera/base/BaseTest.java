package com.coursera.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.coursera.utils.DriverManager;
import com.coursera.utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.getUrl());
        driver.manage().window().maximize();
    }
    
    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();
    }
}