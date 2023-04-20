package com.example.wfcapp;

import java.util.ArrayList;

public class Session {
    private String name;
    private String sessionId;
    private ArrayList<String> bookedCustomers;
    private float price;
    private int availableSlots;
    private int attendedCustomers;
    private ArrayList<String> customerReviews;
    private ArrayList<Float> customerRatings;
    private int week;

    public Session(String name, String sessionId, ArrayList<String> bookedCustomers, float price, int availableSlots, int attendedCustomers, ArrayList<String> customerReviews, ArrayList<Float> customerRatings, int week) {
        this.name = name;
        this.sessionId = sessionId;
        this.bookedCustomers = bookedCustomers;
        this.price = price;
        this.availableSlots = availableSlots;
        this.attendedCustomers = attendedCustomers;
        this.customerReviews = customerReviews;
        this.customerRatings = customerRatings;
        this.week = week;
    }

    public static float getAverageRating(ArrayList<Float> customerRatings) {
        float sum = 0;
        if (customerRatings.size() == 0) {
            return 0;
        } else {
            for (float rating : customerRatings) {
                sum += rating;
            }
        }
        return sum / customerRatings.size();
    }

    public static String printCustomerReviews(ArrayList<String> customerReviews) {
        StringBuilder reviews = new StringBuilder();
        if (customerReviews.size() < 1) {
            return "No customer reviews yet";
        } else {
            int counter = 1;
            for (String review : customerReviews) {
                reviews.append("\n").append(counter).append('.').append(" ").append(review);
                counter++;
            }
        }
        return reviews.toString();
    }

    public static float totalIncomeGenerated (float price, int attendedCustomers) {
        return price * attendedCustomers;
    }

    public String getName() {return this.name;}
    public String getSessionId() {return this.sessionId;}
    public String addToBookedCustomers(String name) {
        bookedCustomers.add(name);
        return name;
    }
    public ArrayList<String> getBookedCustomers() {return this.bookedCustomers;}
    public float getPrice() {return this.price;}
    public int reduceAvailableSlots() {
        this.availableSlots--;
        return this.availableSlots;
    }
    public int increaseAvailableSlots() {
        this.availableSlots++;
        return this.availableSlots;
    }
    public int getAvailableSlots() {return this.availableSlots;}
    public int increaseAttendedCustomers() {
        this.attendedCustomers++;
        return this.attendedCustomers;
    }
    public int getAttendedCustomers() {return this.attendedCustomers;}
    public ArrayList<String> getCustomerReviews() {return this.customerReviews;}
    public ArrayList<Float> getCustomerRatings() {return this.customerRatings;}
    public int getWeek() {return this.week;}
}
