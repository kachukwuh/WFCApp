package com.example.wfcapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private ArrayList<String> validDays;
    private String userFitness;
    private String userWeek;
    private String userFitnessId;
    private ArrayList<String> fitnessTypes;
    private ArrayList<String> fitnessWeeks;
    private ArrayList<String> validUserRatings;

    public Booking() {
        this.validDays = new ArrayList<>(List.of("saturday", "sunday"));
        this.fitnessTypes = new ArrayList<>(List.of("yoga", "spin", "zumba", "body-sculpt"));
        this.fitnessWeeks = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8"));
        this.userFitness = "";
        this.userWeek = "";
        this.userFitnessId = "";
        this.validUserRatings = new ArrayList<>(List.of("1", "2", "3", "4", "5"));

    }

    Scanner scanner = new Scanner(System.in);
    Timetable tt = new Timetable();
    Customer cm = new Customer("", "");


    public void bookCustomer() {
        while (true) {
            System.out.println("Enter 'Saturday', 'Sunday', 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to view timetable or 'back' to go back:");
            String userInput = scanner.next();
            if (getValidDays().contains(userInput.toLowerCase()) || getFitnessTypes().contains(userInput.toLowerCase())) {
                tt.displayTimetable(userInput);
                do {
                    System.out.println("Enter 'Yoga', 'Spin', 'Zumba' or 'Body-Sculpt' to book a lesson:");
                    setUserFitness(scanner.next());
                } while (!getFitnessTypes().contains(getUserFitness().toLowerCase()));

                do {
                    System.out.println("Enter 1, 2, 3, 4, 5, 6, 7, 8 to select a week.");
                    setUserWeek(scanner.next());
                } while (!getFitnessWeeks().contains(getUserWeek()));

                userInput = getUserFitness() + "-" + getUserWeek();

                System.out.println("Please, enter your First Name: ");
                cm.setFirstName(scanner.next().toLowerCase());
                System.out.println("Please, enter your Last Name: ");
                cm.setLastName(scanner.next().toLowerCase());

                tt.createSessions();
                for (Session session : tt.getSessions()) {
                    if (userInput.equalsIgnoreCase(session.getSessionId())) {
                        if (session.getAvailableSlots() < 1) {
                            System.out.println("Sorry, this session is already fully booked....");
                        } else if (session.getBookedCustomers().contains(cm.getUserId())) {
                            System.out.println("Sorry, you have already booked this session...");
                        } else {
                            session.addToBookedCustomers(cm.getUserId());
                            session.reduceAvailableSlots();
                            System.out.println("You have been successfully booked...");
                        }
                        break;
                    }
                }
            } else if (userInput.equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry...");
            }
        }
    }

    public void attendLesson() {
        while(true) {
            System.out.println("Enter 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to attend or 'back' to go back:");
            setUserFitness(scanner.next());
            if (getFitnessTypes().contains(getUserFitness().toLowerCase())) {
                do {
                    System.out.println("Enter 1, 2, 3, 4, 5, 6, 7, 8 to select a week.");
                    setUserWeek(scanner.next());
                } while (!getFitnessWeeks().contains(getUserWeek()));

                setUserFitnessId();

                System.out.println("Please, enter your First Name: ");
                cm.setFirstName(scanner.next().toLowerCase());
                System.out.println("Please, enter your Last Name: ");
                cm.setLastName(scanner.next().toLowerCase());

                for (Session session : tt.getSessions()) {
                    if (getUserFitnessId().equalsIgnoreCase(session.getSessionId())) {
                        if (session.getBookedCustomers().contains(cm.getUserId())) {
                            session.increaseAttendedCustomers();
                            session.increaseAvailableSlots();
                            session.getBookedCustomers().remove(cm.getUserId());
                            System.out.println("Thank you for attending this session! Hope to you again...");
                            System.out.println();

                            String userChoice;
                            ArrayList<String> choices = new ArrayList<>(List.of("yes", "no"));
                            ArrayList<String> ratings = new ArrayList<>(List.of("1", "2", "3", "4", "5"));
                            do {
                                System.out.println("Do you wish to write a review and rate our services? Enter 'Yes' or 'No:'");
                                userChoice = scanner.next();
                            } while (!choices.contains(userChoice.toLowerCase()));

                            if (userChoice.equalsIgnoreCase("no")) {
                                System.out.println("Thank you for your time, Goodbye...");
                                break;
                            } else {
                                System.out.println("Write a review: ");
                                String userReview = scanner.nextLine();
                                session.getCustomerReviews().add(userReview);
                                System.out.println();

                                do {
                                    System.out.println("Please leave a rating: 1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");
                                    userChoice = scanner.next();
                                } while (!ratings.contains(userChoice));

                                session.getCustomerRatings().add(Integer.parseInt(userChoice));
                                System.out.println();

                            }
                            System.out.println("Thank you for your time, Goodbye...");
                        } else {
                            System.out.println("Sorry, you are not booked for this session...");
                        }
                        break;
                    }
                }
            } else if (getUserFitness().equalsIgnoreCase("back")) {
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
    public ArrayList<String> getValidDays() {return this.validDays;}
    public void setUserFitnessId() {this.userFitnessId = getUserFitness() + "-" + getUserWeek();}
    public String getUserFitnessId() {return this.userFitnessId;}
    public ArrayList<String> getValidUserRatings() {return this.validUserRatings;}


    public static void main(String[] args) {
        Booking bk = new Booking();
        Timetable tt = new Timetable();
        tt.createSessions();

        bk.bookCustomer();
    }
}
