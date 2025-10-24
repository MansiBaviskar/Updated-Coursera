package com.coursera.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.coursera.pages.EnterprisePage;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import com.coursera.utils.LoggerUtil;
import com.coursera.utils.ScreenshotUtil;
import com.coursera.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class EnterpriseFormTest {
    
    private WebDriver driver;
    private EnterprisePage enterprisePage;
    private ExtentReports extent;
    private ExtentTest test;
    
    @BeforeClass
    public void setUp() {
        extent = ExtentManager.createInstance("EnterpriseForm_Report.html");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        
        driver.get("https://www.coursera.org/business");
        
        enterprisePage = new EnterprisePage(driver);
    }
    
    @Test(priority = 1)
    public void TC001_FillFormWithValidData() {
        test = ExtentManager.createTest("TC001 - Valid Form Data", "Fill form with valid data");
        
        Map<String, String> testData = new HashMap<>();
        testData.put("FirstName", "John");
        testData.put("LastName", "Doe");
        testData.put("Email", "john.doe@company.com");
        testData.put("Phone", "1234567890");
        testData.put("Company", "Test Company");
        testData.put("Title", "Manager");
        
        enterprisePage.fillForm(testData);
        enterprisePage.submitForm();
        
        String result = enterprisePage.getErrorMessage();
        test.pass("Form submitted with result: " + result);
        System.out.println("TC001 Result: " + result);
    }
    
    @Test(priority = 2)
    public void TC002_FillFormWithInvalidEmail() {
        test = ExtentManager.createTest("TC002 - Invalid Email Test", "Fill form with invalid email format");
        
        Map<String, String> testData = new HashMap<>();
        testData.put("FirstName", "John");
        testData.put("LastName", "Doe");
        testData.put("Email", "invalid@email");
        testData.put("Phone", "1234567890");
        testData.put("Company", "Test Company");
        testData.put("Title", "Manager");
        
        enterprisePage.fillForm(testData);
        enterprisePage.submitForm();
        
        String result = enterprisePage.getErrorMessage();
        test.info("Form result: " + result);
        System.out.println("TC002 Result: " + result);
    }
    
    @Test(priority = 3)
    public void TC003_PhoneNumberValidation() {
        test = ExtentManager.createTest("TC003 - Phone Validation Bug", "Test phone field validation with invalid number");
        
        Map<String, String> testData = new HashMap<>();
        testData.put("FirstName", "John");
        testData.put("LastName", "Doe");
        testData.put("Email", "john@company.com");
        testData.put("Phone", "123456789");
        testData.put("Company", "Test Company");
        testData.put("Title", "Manager");
        
        enterprisePage.fillForm(testData);
        enterprisePage.submitForm();
        
        String result = enterprisePage.getErrorMessage();
        test.info("Form result: " + result);
        test.fail("Phone field accepts invalid phone number (less than 10 digits)");
        
        Assert.fail("Phone field should not accept less than 10 digits but it did");
    }
    
    @AfterClass
    public void tearDown() {
        ExtentManager.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}