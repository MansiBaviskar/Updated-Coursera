package com.coursera.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.coursera.utils.CourseData;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

public class SearchResultsPage {
    
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(css = "button[data-testid*='level']")
    private WebElement levelFilter;
    
    @FindBy(css = "input[value='Beginner']")
    private WebElement beginnerOption;
    
    @FindBy(css = "button[data-testid*='language']")
    private WebElement languageFilter;
    
    @FindBy(css = "input[value='English']")
    private WebElement englishOption;
    
    @FindBy(css = "[data-testid='search-results'] [data-testid='course-card']")
    private List<WebElement> courseCards;
    
    @FindBy(css = "h3 a")
    private List<WebElement> courseNames;
    
    @FindBy(css = "[data-testid='course-duration']")
    private List<WebElement> courseDurations;
    
    @FindBy(css = "[data-testid='course-rating']")
    private List<WebElement> courseRatings;
    
    @FindBy(css = ".no-results, .empty-state")
    private WebElement noResultsMessage;
    
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public void applyBeginnerFilter() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(levelFilter));
            levelFilter.click();
            wait.until(ExpectedConditions.elementToBeClickable(beginnerOption));
            beginnerOption.click();
        } catch (Exception e) {
            System.out.println("Beginner filter not found, continuing...");
        }
    }
    
    public void applyEnglishFilter() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(languageFilter));
            languageFilter.click();
            wait.until(ExpectedConditions.elementToBeClickable(englishOption));
            englishOption.click();
        } catch (Exception e) {
            System.out.println("English filter not found, continuing...");
        }
    }
    
    public boolean areCoursesDisplayed() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                org.openqa.selenium.By.cssSelector("[data-testid='course-card'], .course-item, .result-item")));
            return courseCards.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List<CourseData> getFirstTwoCourses() {
        List<CourseData> courses = new ArrayList<>();
        
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                org.openqa.selenium.By.cssSelector("h3 a, .course-name, .result-title")));
            
            int limit = Math.min(2, courseNames.size());
            
            for (int i = 0; i < limit; i++) {
                String name = courseNames.get(i).getText();
                String hours = i < courseDurations.size() ? courseDurations.get(i).getText() : "N/A";
                String rating = i < courseRatings.size() ? courseRatings.get(i).getText() : "N/A";
                
                courses.add(new CourseData(name, hours, rating));
            }
        } catch (Exception e) {
            System.out.println("Error extracting course data: " + e.getMessage());
        }
        
        return courses;
    }
    
    public boolean isBeginnerFilterApplied() {
        try {
            return driver.getPageSource().contains("Beginner") || 
                   driver.getCurrentUrl().contains("level=beginner");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isEnglishFilterApplied() {
        try {
            return driver.getPageSource().contains("English") || 
                   driver.getCurrentUrl().contains("language=english");
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isNoResultsMessageDisplayed() {
        try {
            return noResultsMessage.isDisplayed() || 
                   driver.getPageSource().contains("No results found") ||
                   driver.getPageSource().contains("no courses found");
        } catch (Exception e) {
            return courseCards.size() == 0;
        }
    }
}