package com.coursera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.coursera.utils.ExcelReporter;
import com.coursera.utils.ExtentLogger;
import java.util.List;

public class SearchPage {
    WebDriver driver;
    String currentTestCase;
    
    By levelFilters = By.xpath("//*[contains(text(),'Level')]");
    By languageFilters = By.xpath("//*[contains(text(),'Language')]");
    By inactiveChips = By.xpath("//button[@data-testid='chip-button-inactive']");
    By englishOption = By.xpath("//span[text()='English']");
    By beginnerOption = By.xpath("//span[text()='Beginner']");
    By viewButton = By.xpath("//span[contains(text(),'View')]");
    By courseNames = By.xpath("//a[@class=\"cds-119 cds-113 cds-115 cds-CommonCard-titleLink css-fdx774 cds-142\"]//h3");
    By courseRatings = By.xpath("//div[@class=\"cds-RatingStat-meter\"]");
    By courseDurations = By.xpath("//div[@class=\"cds-CommonCard-metadata\"]//p");
    
    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setTestCase(String testCase) {
        this.currentTestCase = testCase;
    }
    
    public void applyFilters() throws InterruptedException {
        ExtentLogger.logInfo("Applying filters: English language and Beginner level");
        
        List<WebElement> levelFiltersList = driver.findElements(levelFilters);
        List<WebElement> languageFiltersList = driver.findElements(languageFilters);
        boolean inactiveChipsPresent = false;
        
        try {
            inactiveChipsPresent = driver.findElement(inactiveChips).isDisplayed();
        } catch (NoSuchElementException e) {
            inactiveChipsPresent = false;
        }

        if (inactiveChipsPresent) {
            languageFiltersList.get(0).click();
            Thread.sleep(2000);
            driver.findElement(englishOption).click();
            driver.findElement(viewButton).click();
            Thread.sleep(3000);

            levelFiltersList.get(0).click();
            Thread.sleep(2000);
            driver.findElement(beginnerOption).click();
            driver.findElement(viewButton).click();
        } else {
            languageFiltersList.get(0).click();
            Thread.sleep(2000);
            driver.findElement(englishOption).click();
            Thread.sleep(3000);

            levelFiltersList.get(0).click();
            Thread.sleep(2000);
            driver.findElement(beginnerOption).click();
        }
        
        ExtentLogger.logPass("Filters applied successfully");
    }
    
    public void printFirstTwoCourses() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> names = driver.findElements(courseNames);
        List<WebElement> ratings = driver.findElements(courseRatings);
        List<WebElement> durations = driver.findElements(courseDurations);
       
        System.out.println("=== WEB DEVELOPMENT COURSES ===");
        ExtentLogger.logInfo("Extracting first 2 course details");
        
        for(int i = 0; i < 2; i++) {
            String courseName = names.get(i).getText();
            String rating = ratings.get(i).getAttribute("aria-valuenow");
            String duration = durations.get(i).getText().split("·")[2].trim();
            
            System.out.println("Course " + (i+1));
            System.out.println("Name: " + courseName);
            System.out.println("Rating: " + rating);
            System.out.println("Duration: " + duration);
            System.out.println("---");
            
            // Log to Excel and Extent
            ExcelReporter.logCourseResult(currentTestCase, courseName, rating, duration);
            ExtentLogger.logCourseResult(courseName, rating, duration);
        }
        
        ExtentLogger.logPass("Successfully extracted 2 course details");
    }
}