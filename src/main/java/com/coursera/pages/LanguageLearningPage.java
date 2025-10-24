package com.coursera.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.coursera.utils.ExcelReporter;
import com.coursera.utils.ExtentLogger;
import java.util.List;

public class LanguageLearningPage {
    WebDriver driver;
    String currentTestCase;
    
    By showMoreButton = By.xpath("//*[text()='Show 43 more']");
    By countsElements = By.xpath("//div[@class='cds-checkboxAndRadio-labelText']/span/span");
    
    public LanguageLearningPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setTestCase(String testCase) {
        this.currentTestCase = testCase;
    }
    
    public void expandLanguageOptions() throws InterruptedException {
        ExtentLogger.logInfo("Expanding language options to show all languages");
        driver.findElement(showMoreButton).click();
        Thread.sleep(2000);
        ExtentLogger.logPass("Language options expanded successfully");
    }
    
    public void printLanguagesAndLevels() {
        List<WebElement> countsElementsList = driver.findElements(countsElements);
        
        System.out.println("\n=== LANGUAGE LEARNING SECTION ===");
        System.out.println("Languages and Course Counts:");
        ExtentLogger.logInfo("Extracting language course counts (47 languages)");
        
        for (int i = 0; i < 47; i++) {
            String labelText = countsElementsList.get(i).getText();
            String language = labelText.replaceAll("\\(\\d+\\)", "").trim();
            String count = labelText.replaceAll("[^0-9]", "");
            
            System.out.println(language + ": " + count + " courses");
            
            // Log to Excel and Extent (only first 10 to avoid cluttering)
            ExcelReporter.logLanguageResult(currentTestCase, language, count + " courses");
            if (i < 10) {
                ExtentLogger.logLanguageResult(language, count + " courses");
            }
        }
        
        System.out.println("\nLevels and Course Counts:");
        ExtentLogger.logInfo("Extracting course levels and counts");
        
        for (int i = 50; i < 54; i++) {
            String labelText = countsElementsList.get(i).getText();
            String level = labelText.replaceAll("\\(\\d+\\)", "").trim();
            String count = labelText.replaceAll("[^0-9]", "");
            
            System.out.println(level + " Level: " + count + " courses");
            
            // Log to Excel and Extent
            ExcelReporter.logLanguageResult(currentTestCase, level + " Level", count + " courses");
            ExtentLogger.logLanguageResult(level + " Level", count + " courses");
        }
        
        ExtentLogger.logPass("Successfully extracted all language and level data");
    }
}