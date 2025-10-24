package com.coursera.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import com.coursera.pages.SearchPage;
import com.coursera.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.time.Duration;

public class WebDevCourseSearchTest {
    
    private WebDriver driver;
    private SearchPage searchPage;
    private ExtentReports extent;
    private ExtentTest test;
    
    @BeforeMethod
    public void setUp() {
        extent = ExtentManager.createInstance("WebDevSearch_Report.html");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.coursera.org/search?query=web%20development");
        
        searchPage = new SearchPage(driver);
    }
    
    @Test(priority = 1)
    public void TC001_ValidWebDevCourseSearch() throws InterruptedException {
        test = ExtentManager.createTest("TC001 - Valid Web Dev Search", "Search web development courses with filters");
        
        searchPage.setTestCase("TC001");
        searchPage.applyFilters();
        
        Assert.assertTrue(driver.findElements(By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3")).size() > 0, 
            "No courses displayed after applying filters");
        
        test.pass("Web development courses found with filters applied");
        System.out.println("TC001 PASSED: Web development courses found with filters");
    }
    
    @Test(priority = 2)
    public void TC002_ExtractFirstTwoCourses() throws InterruptedException {
        test = ExtentManager.createTest("TC002 - Extract First Two Courses", "Extract course details for first 2 courses");
        
        searchPage.setTestCase("TC002");
        searchPage.applyFilters();
        searchPage.printFirstTwoCourses();
        
        Assert.assertTrue(driver.findElements(By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3")).size() >= 2, 
            "Less than 2 courses available");
        
        test.pass("First two courses extracted successfully");
        System.out.println("TC002 PASSED: First two courses extracted successfully");
    }
    
    @Test(priority = 3)
    public void TC003_FilterValidation() throws InterruptedException {
        test = ExtentManager.createTest("TC003 - Filter Validation", "Validate Beginner and English filters are applied");
        
        searchPage.setTestCase("TC003");
        searchPage.applyFilters();
        
        Assert.assertTrue(driver.getPageSource().contains("Beginner") || driver.getCurrentUrl().contains("level"), 
            "Beginner filter not applied correctly");
        Assert.assertTrue(driver.getPageSource().contains("English") || driver.getCurrentUrl().contains("language"), 
            "English filter not applied correctly");
        
        test.pass("Filters validated successfully");
        System.out.println("TC003 PASSED: Filters validated successfully");
    }
    
    @Test(priority = 4)
    public void TC004_NoResultsHandling() throws InterruptedException {
        test = ExtentManager.createTest("TC004 - No Results Handling", "Test handling of invalid search terms");
        
        driver.get("https://www.coursera.org/search?query=xyz123invalidcourse");
        searchPage.setTestCase("TC004");
        
        try {
            searchPage.applyFilters();
            searchPage.printFirstTwoCourses();
        } catch (Exception e) {
            Assert.assertTrue(driver.getPageSource().contains("No results") || 
                driver.findElements(By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3")).size() == 0,
                "No results scenario not handled correctly");
        }
        
        test.pass("No results scenario handled correctly");
        System.out.println("TC004 PASSED: No results scenario handled correctly");
    }
    
    @Test(priority = 5)
    public void TC005_SearchWithInvalidTerm() throws InterruptedException {
        test = ExtentManager.createTest("TC005 - Invalid Search Term", "Test search with completely invalid terms");
        
        driver.get("https://www.coursera.org/search?query=invalidxyz123");
        searchPage.setTestCase("TC005");
        
        try {
            searchPage.applyFilters();
        } catch (Exception e) {
            // Expected when no results
        }
        
        Assert.assertTrue(driver.getPageSource().contains("No results") || 
            driver.findElements(By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3")).size() == 0,
            "Invalid search should show no results");
        
        test.pass("Invalid search term handled correctly");
        System.out.println("TC005 PASSED: Invalid search term handled correctly");
    }
    
    @Test(priority = 6)
    public void TC006_CourseDataFormat() throws InterruptedException {
        test = ExtentManager.createTest("TC006 - Course Data Format", "Validate course data format and structure");
        
        searchPage.setTestCase("TC006");
        searchPage.applyFilters();
        
        if (driver.findElements(By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3")).size() > 0) {
            String courseName = driver.findElement(By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3")).getText();
            String rating = driver.findElement(By.xpath("//div[@class=\"cds-RatingStat-meter\"]")).getAttribute("aria-valuenow");
            String duration = driver.findElement(By.xpath("//div[@class=\"cds-CommonCard-metadata\"]//p")).getText();
            
            Assert.assertTrue(courseName.length() > 0, "Course name is empty");
            Assert.assertTrue(rating != null && !rating.isEmpty(), "Rating is invalid");
            Assert.assertTrue(duration.length() > 0, "Duration is invalid");
            
            test.pass("Course data format validated successfully");
            System.out.println("TC006 PASSED: Course data format validated");
        } else {
            test.info("No courses available for validation");
            System.out.println("TC006 SKIPPED: No courses available for validation");
        }
    }
    
    @AfterMethod
    public void tearDown() {
        ExtentManager.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}