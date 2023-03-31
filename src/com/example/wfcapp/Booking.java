package com.example.wfcapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private ArrayList<String> validInputs;
    private String userFitness;
    private String userWeek;
    private ArrayList<String> fitnessTypes;
    private ArrayList<String> fitnessWeeks;

    public Booking() {
        this.validInputs = new ArrayList<>(List.of("saturday", "sunday", "yoga", "spin", "zumba", "body-sculpt"));
        this.fitnessTypes = new ArrayList<>(List.of("yoga", "spin", "zumba", "body-sculpt"));
        this.fitnessWeeks = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8"));
        this.userFitness = "";
        this.userWeek = "";

    }

    Scanner scanner = new Scanner(System.in);
    Timetable tt = new Timetable();
    Customer cm = new Customer("", "");


    public void bookCustomer() {
        while (true) {
            System.out.println("Enter 'Saturday', 'Sunday', 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to view timetable or 'exit' to quit:");
            String userInput = scanner.next();
            if (getValidInputs().contains(userInput.toLowerCase())) {
                tt.displayTimetable(userInput);
                do {
                    System.out.println("Enter 'Yoga', 'Spin', 'Zumba' or 'Body-Sculpt' to book a lesson:");
                    setUserFitness(scanner.next());
                } while (!getFitnessTypes().contains(getUserFitness().toLowerCase()));

                do {
                    System.out.println("Enter 1, 2, 3, 4, 5, 6, 7, 8 to select a week.");
                    setUserWeek(scanner.next());
                } while (!getFitnessWeeks().contains(getUserWeek().toLowerCase()));

                userInput = getUserFitness() + "-" + getUserWeek();

                System.out.println("Please, enter your First Name: ");
                cm.setFirstName(scanner.next().toLowerCase());
                System.out.println("Please, enter your Last Name: ");
                cm.setLastName(scanner.next().toLowerCase());

                System.out.println(cm.getUserId());

                for (Session session : tt.getSessions()) {
                    if (userInput.equalsIgnoreCase(session.getSessionId())) {
                        if (session.getAvailableSlots() < 1) {
                            System.out.println("Sorry, this session is already fully booked....");
                        } else if (session.getBookedCustomers().contains(cm.getUserId())) {
                            System.out.println("Sorry, you have already booked this session...");
                        } else {
                            session.addToBookedCustomers(cm.getUserId());
                            session.setAvailableSlots();
                            System.out.println("You have been successfully booked...");
                        }
                    }
                }






            } else if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for coming! Goodbye...");
                break;
            } else {
                System.out.println("Sorry, Invalid entry...");
            }
        }
    }



    public void setUserFitness(String userFitness) {this.userFitness = userFitness;}
    public String getUserFitness() {return this.userFitness;}
    public void setUserWeek(String userWeek) {this.userWeek = userWeek;}
    public String getUserWeek() {return this.userWeek;}
    public ArrayList<String> getFitnessTypes() {return this.fitnessTypes;}
    public ArrayList<String> getFitnessWeeks() {return this.fitnessWeeks;}
    public ArrayList<String> getValidInputs() {return this.validInputs;}


    public static void main(String[] args) {
        Booking bk = new Booking();
        Timetable tt = new Timetable();
        tt.createSessions();
//        for (Session session : tt.getSessions()) {
//            System.out.println(session.getFitnessId());
//        }

        bk.bookCustomer();
    }
}
