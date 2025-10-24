package com.coursera.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.coursera.pages.LanguageLearningPage;
import com.coursera.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.time.Duration;

public class LanguageLearningTest {
    
    private WebDriver driver;
    private LanguageLearningPage languagePage;
    private ExtentReports extent;
    private ExtentTest test;
    
    @BeforeClass
    public void setUp() {
        extent = ExtentManager.createInstance("LanguageLearning_Report.html");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        
        driver.get("https://www.coursera.org/browse/language-learning");
        
        languagePage = new LanguageLearningPage(driver);
    }
    
    @Test(priority = 1)
    public void TC001_ExpandLanguageOptions() throws InterruptedException {
        test = ExtentManager.createTest("TC001 - Expand Language Options", "Click show more to expand language list");
        
        languagePage.setTestCase("TC001");
        languagePage.expandLanguageOptions();
        
        test.pass("Language options expanded successfully");
        System.out.println("TC001 PASSED: Language options expanded successfully");
    }
    
    @Test(priority = 2)
    public void TC002_ExtractLanguagesAndLevels() {
        test = ExtentManager.createTest("TC002 - Extract Languages and Levels", "Extract all languages and levels with course counts");
        
        languagePage.setTestCase("TC002");
        languagePage.printLanguagesAndLevels();
        
        test.pass("Languages and levels extracted successfully");
        System.out.println("TC002 PASSED: Languages and levels extracted successfully");
    }
    
    @AfterClass
    public void tearDown() {
        ExtentManager.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}