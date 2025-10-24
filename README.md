# Coursera Automation - Hybrid Framework

## Framework Components

### 1. **Page Object Model (POM)**
- `src/main/java/com/coursera/pages/`
- Separate classes for each page with locators and methods

### 2. **Data Driven Framework**
- `src/main/resources/testdata/CourseraData.csv` - Test data
- `src/main/java/com/coursera/utils/ExcelUtils.java` - Excel operations
- `@DataProvider` in test classes for parameterized testing

### 3. **Keyword Driven Framework**
- `src/main/java/com/coursera/keywords/Keywords.java` - Reusable actions
- High-level business methods combining page actions

## Data Driven Features

### Test Data Structure:
```
TestCase | SearchTerm      | FirstName | LastName | Email
TC001    | Web Development | Mansi     | Baviskar | invalid@email
TC002    | Java Programming| John      | Doe      | test@invalid
```

### Usage:
```java
@Test(dataProvider = "testData")
public void testCourseraAutomation(String testCase) {
    // Uses data from Excel for each test case
}

@DataProvider
public Object[][] testData() {
    return ExcelUtils.getAllTestData("CourseraData");
}
```

## How to Run
1. Convert `CourseraData.csv` to Excel format
2. Import project into Eclipse
3. Run as TestNG Test

## Framework Benefits
- **Data Driven**: Multiple test scenarios from Excel
- **Page Object Model**: Maintainable page classes  
- **Keyword Driven**: Reusable business actions
- **Configuration Driven**: Properties file for settings