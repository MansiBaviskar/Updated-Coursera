package com.coursera.utils;

import org.apache.logging.log4j.LogManager;

public class LoggerUtil {
    
    private static org.apache.logging.log4j.Logger logger;
    
    static {
        try {
            logger = LogManager.getLogger(LoggerUtil.class);
        } catch (Exception e) {
            System.out.println("Logger initialization failed: " + e.getMessage());
        }
    }
    
    public static void info(String message) {
        try {
            if (logger != null) {
                logger.info(message);
            } else {
                System.out.println("INFO: " + message);
            }
        } catch (Exception e) {
            System.out.println("INFO: " + message);
        }
    }
    
    public static void error(String message) {
        try {
            if (logger != null) {
                logger.error(message);
            } else {
                System.out.println("ERROR: " + message);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + message);
        }
    }
    
    public static void debug(String message) {
        try {
            if (logger != null) {
                logger.debug(message);
            } else {
                System.out.println("DEBUG: " + message);
            }
        } catch (Exception e) {
            System.out.println("DEBUG: " + message);
        }
    }
    
    public static void warn(String message) {
        try {
            if (logger != null) {
                logger.warn(message);
            } else {
                System.out.println("WARN: " + message);
            }
        } catch (Exception e) {
            System.out.println("WARN: " + message);
        }
    }
}