package com.coursera.pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.Map;
 
public class EnterprisePage {
    WebDriver driver;
    
    By firstNameField = By.xpath("//*[@id=\"FirstName\"]");
    By lastNameField = By.xpath("//*[@id=\"LastName\"]");
    By emailField = By.xpath("//*[@id=\"Email\"]");
    By phoneField = By.xpath("//*[@id=\"Phone\"]");
    By organizationTypeField = By.xpath("//*[@id='rentalField9']");
    By companyField = By.xpath("//*[@id='Company']");
    By employeeRangeField = By.xpath("//*[@id='Employee_Range__c']");
    By countryField = By.xpath("//*[@id=\"Country\"]");
    By titleField = By.xpath("//*[@id=\"Title\"]");
    By submitButton = By.xpath("//button[@class=\"mktoButton\"]");
    By errorMessage = By.xpath("//*[@id=\"ValidMsgEmail\"]");
    By JobTitle = By.xpath("//*[@id=\"Title\"]");
    By Describes = By.xpath("//*[@id=\"What_the_lead_asked_for_on_the_website__c\"]");
    By State = By.xpath("//*[@id=\"State\"]");
    public EnterprisePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void fillForm(Map<String, String> testData) {
        try {
            Thread.sleep(3000); // Wait for form to load
            
            driver.findElement(firstNameField).clear();
            driver.findElement(firstNameField).sendKeys(testData.get("FirstName"));
            
            driver.findElement(lastNameField).clear();
            driver.findElement(lastNameField).sendKeys(testData.get("LastName"));
            
            driver.findElement(emailField).clear();
            driver.findElement(emailField).sendKeys(testData.get("Email"));
            
            driver.findElement(phoneField).clear();
            driver.findElement(phoneField).sendKeys(testData.get("Phone"));
            
            try {
                WebElement orgType = driver.findElement(organizationTypeField);
                Select orgTypeSelect = new Select(orgType);
                orgTypeSelect.selectByVisibleText("Business");
            } catch (Exception e) {
                System.out.println("Organization type field not found or not selectable");
            }
            driver.findElement(JobTitle).clear();
            driver.findElement(JobTitle).sendKeys(testData.get("Company"));
            
            
            driver.findElement(companyField).clear();
            driver.findElement(companyField).sendKeys(testData.get("Company"));
            
            try {
                WebElement empRange = driver.findElement(employeeRangeField);
                Select empRangeSelect = new Select(empRange);
                empRangeSelect.selectByVisibleText("501-1000");
            } catch (Exception e) {
                System.out.println("Employee range field not found or not selectable");
            }
            
            try {
                WebElement describe = driver.findElement(Describes);
                Select describeSelect = new Select(describe);
                describeSelect.selectByVisibleText("Courses for myself");
            } catch (Exception e) {
                System.out.println("Description field not found or not selectable");
            }
            
            try {
                WebElement country = driver.findElement(countryField);
                Select countrySelect = new Select(country);
                countrySelect.selectByVisibleText("India");
            } catch (Exception e) {
                System.out.println("Country field not found or not selectable");
            }
            
            try {
                WebElement state = driver.findElement(State);
                Select stateSelect = new Select(state);
                stateSelect.selectByVisibleText("Maharashtra");
            } catch (Exception e) {
                System.out.println("State field not found or not selectable");
            }
            
            driver.findElement(titleField).clear();
            driver.findElement(titleField).sendKeys(testData.get("Title"));
            
        } catch (Exception e) {
            System.out.println("Error filling form: " + e.getMessage());
            throw new RuntimeException("Form filling failed: " + e.getMessage());
        }
    }
    
    public void submitForm() {
        try {
            Thread.sleep(1000);
            WebElement submit = driver.findElement(submitButton);
            submit.click();
            Thread.sleep(2000); // Wait for submission
        } catch (Exception e) {
            System.out.println("Submit button not found or not clickable: " + e.getMessage());
            throw new RuntimeException("Form submission failed: " + e.getMessage());
        }
    }
    
    public String getErrorMessage() {
        try {
            Thread.sleep(3000); // Wait for error to appear
            WebElement errorElement = driver.findElement(errorMessage);
            if (errorElement.isDisplayed()) {
                return errorElement.getText();
            } else {
                return "Form submitted successfully or no validation error";
            }
        } catch (Exception e) {
            // Check for other possible error messages
            try {
                WebElement generalError = driver.findElement(By.xpath("//*[contains(text(),'error') or contains(text(),'Error') or contains(text(),'invalid') or contains(text(),'Invalid')]"));
                return generalError.getText();
            } catch (Exception ex) {
                return "Form validation completed - no specific error message found";
            }
        }
    }
}