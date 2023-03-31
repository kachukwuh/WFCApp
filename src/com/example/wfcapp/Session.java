package com.example.wfcapp;

import java.util.ArrayList;

public class Session {
    private String fitnessId;
    private ArrayList<String> bookedCustomers;
    private Double price;
    private Integer availableSlots;
    private Integer attendedCustomers;
    private ArrayList<String> customerReviews;
    private ArrayList<Integer> customerRatings;

    public Session(String fitnessId, ArrayList<String> bookedCustomers, Double price, Integer availableSlots, Integer attendedCustomers, ArrayList<String> customerReviews, ArrayList<Integer> customerRatings) {
        this.fitnessId = fitnessId;
        this.bookedCustomers = bookedCustomers;
        this.price = price;
        this.availableSlots = availableSlots;
        this.attendedCustomers = attendedCustomers;
        this.customerReviews = customerReviews;
        this.customerRatings = customerRatings;
    }

    public String getFitnessId() {return this.fitnessId;}
    public ArrayList<String> getBookedCustomers() {return this.bookedCustomers;}
    public Double getPrice() {return this.price;}
    public Integer getAvailableSlots() {return this.availableSlots;}
    public Integer getAttendedCustomers() {return this.attendedCustomers;}
    public ArrayList<String> getCustomerReviews() {return this.customerReviews;}
    public ArrayList<Integer> getCustomerRatings() {return this.customerRatings;}
}
