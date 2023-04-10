package com.example.wfcapp;

public class Lesson {
    private String name;
    private String lessonTime;
    private String day;
    private Double price;

    public Lesson(String name, String lessonTime, String day, Double price) {
        this.name = name;
        this.lessonTime = lessonTime;
        this.day = day;
        this.price = price;
    }

    public String getName() {return this.name;}
    public String getLessonTime() {return this.lessonTime;}
    public String getDay() {return this.day;}
    public Double getPrice() {return this.price;}
}
