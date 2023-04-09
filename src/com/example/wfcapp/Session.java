package com.example.wfcapp;

import java.util.ArrayList;

public class Session {
    private String sessionId;
    private ArrayList<String> bookedCustomers;
    private double price;
    private int availableSlots;
    private int attendedCustomers;
    private ArrayList<String> customerReviews;
    private ArrayList<Integer> customerRatings;
    private int week;

    public Session(String sessionId, ArrayList<String> bookedCustomers, double price, int availableSlots, int attendedCustomers, ArrayList<String> customerReviews, ArrayList<Integer> customerRatings, int week) {
        this.sessionId = sessionId;
        this.bookedCustomers = bookedCustomers;
        this.price = price;
        this.availableSlots = availableSlots;
        this.attendedCustomers = attendedCustomers;
        this.customerReviews = customerReviews;
        this.customerRatings = customerRatings;
        this.week = week;
    }

    public String getSessionId() {return this.sessionId;}
    public void addToBookedCustomers(String name) {bookedCustomers.add(name);}
    public ArrayList<String> getBookedCustomers() {return this.bookedCustomers;}
    public double getPrice() {return this.price;}
    public void reduceAvailableSlots() {this.availableSlots--;}
    public void increaseAvailableSlots() {this.availableSlots++;}
    public int getAvailableSlots() {return this.availableSlots;}
    public void increaseAttendedCustomers() {this.attendedCustomers++;}
    public int getAttendedCustomers() {return this.attendedCustomers;}
    public ArrayList<String> getCustomerReviews() {return this.customerReviews;}
    public ArrayList<Integer> getCustomerRatings() {return this.customerRatings;}
    public int getWeek() {return this.week;}
}
