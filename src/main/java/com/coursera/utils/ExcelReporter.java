package com.coursera.utils;

public class ExcelReporter {
    
    public static void logCourseResult(String testCase, String courseName, String rating, String duration) {
        System.out.println("EXCEL LOG - TestCase: " + testCase + " | Course: " + courseName + 
                          " | Rating: " + rating + " | Duration: " + duration);
    }
    
    public static void logLanguageResult(String testCase, String language, String count) {
        System.out.println("EXCEL LOG - TestCase: " + testCase + " | Language: " + language + " | Count: " + count);
    }
    
    public static void logErrorResult(String testCase, String errorType, String errorMessage) {
        System.out.println("EXCEL LOG - TestCase: " + testCase + " | Error Type: " + errorType + " | Message: " + errorMessage);
    }
    
    public static void logCourseResult(String testCase, String courseName, String rating, String duration) {
        System.out.println("EXCEL LOG - TestCase: " + testCase + " | Course: " + courseName + 
                          " | Rating: " + rating + " | Duration: " + duration);
    }
}