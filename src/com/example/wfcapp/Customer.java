package com.example.wfcapp;

public class Customer {
    private String firstName;
    private String lastName;
    private String userId;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = this.firstName + this.lastName;
    }

    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public String getUserId() {return this.userId;}
}
