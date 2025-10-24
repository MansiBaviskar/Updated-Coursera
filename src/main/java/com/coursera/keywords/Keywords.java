package com.coursera.keywords;

import org.openqa.selenium.WebDriver;
import com.coursera.pages.*;
import com.coursera.utils.ExcelDataProvider;
import com.coursera.utils.ExcelReporter;
import com.coursera.utils.ExtentLogger;
import java.util.Map;

public class Keywords {
    WebDriver driver;
    HomePage homePage;
    SearchPage searchPage;
    LanguageLearningPage languagePage;
    EnterprisePage enterprisePage;
    
    public Keywords(WebDriver driver) {
        this.driver = driver;
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        languagePage = new LanguageLearningPage(driver);
        enterprisePage = new EnterprisePage(driver);
    }
    
    public void searchWebDevelopmentCourses(String testCase) throws InterruptedException {
        String searchTerm = ExcelDataProvider.getCellData("CourseraData", testCase, "SearchTerm");
        ExtentLogger.logInfo("Starting course search for: " + searchTerm);
        
        searchPage.setTestCase(testCase);
        homePage.searchCourse(searchTerm);
        searchPage.applyFilters();
        searchPage.printFirstTwoCourses();
        
        ExtentLogger.logPass("Course search completed successfully");
    }
    
    public void exploreLanguageLearning(String testCase) throws InterruptedException {
        ExtentLogger.logInfo("Starting Language Learning exploration");
        
        languagePage.setTestCase(testCase);
        homePage.clickExplore();
        homePage.clickLanguageLearning();
        languagePage.expandLanguageOptions();
        languagePage.printLanguagesAndLevels();
        
        ExtentLogger.logPass("Language Learning exploration completed");
    }
    
    public void fillEnterpriseForm(String testCase) throws InterruptedException {
        ExtentLogger.logInfo("Starting Enterprise Form submission");
        
        try {
            driver.navigate().to("https://www.coursera.org/");
            Thread.sleep(5000);
            driver.navigate().refresh();
            Thread.sleep(3000);
            
            homePage.clickEnterprise();
            
            Map<String, String> testData = ExcelDataProvider.getTestData("CourseraData", testCase);
            ExtentLogger.logInfo("Filling form with test data for: " + testCase);
            
            enterprisePage.fillForm(testData);
            enterprisePage.submitForm();
            
            String errorMessage = enterprisePage.getErrorMessage();
            System.out.println("\n=== FORM VALIDATION ERROR ===");
            System.out.println("Error Message: " + errorMessage);
            
            // Log to Excel and Extent
            ExcelReporter.logFormResult(testCase, errorMessage, "COMPLETED");
            ExtentLogger.logFormResult(errorMessage, "COMPLETED");
            
        } catch (Exception e) {
            String errorMsg = "Form submission failed - " + e.getMessage();
            System.out.println("\n=== FORM VALIDATION ERROR ===");
            System.out.println("Error Message: " + errorMsg);
            
            // Log to Excel and Extent
            ExcelReporter.logFormResult(testCase, errorMsg, "FAILED");
            ExtentLogger.logFormResult(errorMsg, "FAILED");
        }
    }
}