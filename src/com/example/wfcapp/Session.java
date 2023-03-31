package com.example.wfcapp;

import java.util.ArrayList;

public class Session {
    private String sessionId;
    private ArrayList<String> bookedCustomers;
    private Double price;
    private Integer availableSlots;
    private Integer attendedCustomers;
    private ArrayList<String> customerReviews;
    private ArrayList<Integer> customerRatings;

    public Session(String sessionId, ArrayList<String> bookedCustomers, Double price, Integer availableSlots, Integer attendedCustomers, ArrayList<String> customerReviews, ArrayList<Integer> customerRatings) {
        this.sessionId = sessionId;
        this.bookedCustomers = bookedCustomers;
        this.price = price;
        this.availableSlots = availableSlots;
        this.attendedCustomers = attendedCustomers;
        this.customerReviews = customerReviews;
        this.customerRatings = customerRatings;
    }

    public String getSessionId() {return this.sessionId;}
    public void addToBookedCustomers(String name) {bookedCustomers.add(name);}
    public ArrayList<String> getBookedCustomers() {return this.bookedCustomers;}
    public Double getPrice() {return this.price;}
    public void setAvailableSlots() {this.availableSlots--;}
    public Integer getAvailableSlots() {return this.availableSlots;}
    public Integer getAttendedCustomers() {return this.attendedCustomers;}
    public ArrayList<String> getCustomerReviews() {return this.customerReviews;}
    public ArrayList<Integer> getCustomerRatings() {return this.customerRatings;}
}
