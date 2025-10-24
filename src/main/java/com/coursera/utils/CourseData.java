package com.coursera.utils;

public class CourseData {
    private String name;
    private String hours;
    private String rating;
    
    public CourseData(String name, String hours, String rating) {
        this.name = name;
        this.hours = hours;
        this.rating = rating;
    }
    
    public String getName() {
        return name;
    }
    
    public String getHours() {
        return hours;
    }
    
    public String getRating() {
        return rating;
    }
    
    @Override
    public String toString() {
        return "Course: " + name + " | Hours: " + hours + " | Rating: " + rating;
    }
}