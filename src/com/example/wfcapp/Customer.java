package com.example.wfcapp;

public class Customer {
    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getFirstName() {return this.firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getLastName() {return this.lastName;}
    public String getUserId() {return this.firstName + this.lastName;}
}
