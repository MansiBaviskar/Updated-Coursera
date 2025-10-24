package com.coursera.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    
    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    
    public static String getUrl() {
        return properties.getProperty("url");
    }
    
    public static String getTestDataFile() {
        return properties.getProperty("testdata.file");
    }
}