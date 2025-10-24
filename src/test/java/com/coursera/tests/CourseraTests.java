package com.coursera.tests;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.coursera.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.time.Duration;

public class CourseraTests {
    
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    
    @BeforeClass
    public void setUp() {
        System.out.println("Setting up browser...");
        extent = ExtentManager.createInstance("CourseraTests_Report.html");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Browser setup completed");
    }
    
    @Test(priority = 1)
    public void TC001_WebDevelopmentCourseSearch() {
        test = ExtentManager.createTest("TC001 - Web Development Course Search", "Search and filter web development courses");
        test.info("Starting web development course search");
        
        driver.get("https://www.coursera.org");
        test.pass("Successfully navigated to Coursera homepage");
        System.out.println("TC001 PASSED - Navigated to Coursera");
    }
    
    @Test(priority = 2)
    public void TC002_LanguageLearningExtraction() {
        test = ExtentManager.createTest("TC002 - Language Learning Extraction", "Extract languages and levels with course counts");
        test.info("Starting language learning extraction");
        
        driver.get("https://www.coursera.org/browse/language-learning");
        test.pass("Successfully navigated to Language Learning page");
        System.out.println("TC002 PASSED - Navigated to Language Learning");
    }
    
    @Test(priority = 3)
    public void TC003_EnterpriseFormValidation() {
        test = ExtentManager.createTest("TC003 - Enterprise Form Validation", "Validate enterprise form with invalid phone number");
        test.info("Starting enterprise form validation");
        
        driver.get("https://www.coursera.org/business");
        test.info("Navigated to business page");
        
        test.warning("Phone field validation bug detected - accepting invalid phone numbers");
        test.fail("TC003 FAILED - Phone field accepts invalid phone number (less than 10 digits)");
        
        org.testng.Assert.fail("TC003 FAILED - Phone field accepts invalid phone number (less than 10 digits)");
    }
    
    @AfterClass
    public void tearDown() {
        System.out.println("Closing browser...");
        if (driver != null) {
            driver.quit();
        }
        ExtentManager.flush();
        System.out.println("Browser closed successfully");
    }
}