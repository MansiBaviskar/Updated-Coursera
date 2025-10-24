package com.coursera.utils;

public class ExtentLogger {
    
    public static void logInfo(String message) {
        System.out.println("INFO: " + message);
    }
    
    public static void logPass(String message) {
        System.out.println("PASS: " + message);
    }
    
    public static void logCourseResult(String courseName, String rating, String duration) {
        System.out.println("COURSE RESULT: " + courseName + " | Rating: " + rating + " | Duration: " + duration);
    }
    
    public static void logLanguageResult(String language, String count) {
        System.out.println("LANGUAGE RESULT: " + language + " | Count: " + count);
    }
    
    public static void logErrorResult(String errorType, String errorMessage) {
        System.out.println("ERROR RESULT: " + errorType + " | Message: " + errorMessage);
    }
}