package com.example.wfcapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private final ArrayList<String> validDays;
    private String userFitness;
    private String userWeek;
    private String userFitnessId;
    private final ArrayList<String> fitnessTypes;
    private final ArrayList<String> fitnessWeeks;

    public Booking() {
        this.validDays = new ArrayList<>(List.of("saturday", "sunday"));
        this.fitnessTypes = new ArrayList<>(List.of("yoga", "spin", "zumba", "body-sculpt"));
        this.fitnessWeeks = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8"));
        this.userFitness = "";
        this.userWeek = "";
        this.userFitnessId = "";
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

                for (Session session : tt.getSessions()) {
                    if (userInput.equalsIgnoreCase(session.getSessionId())) {
                        if (session.getAvailableSlots() < 1) {
                            System.out.println("Sorry, This session is already fully booked....");
                            System.out.println();
                        } else if (session.getBookedCustomers().contains(cm.getUserId())) {
                            System.out.println("Sorry, You have already booked this session...");
                            System.out.println();
                        } else {
                            session.addToBookedCustomers(cm.getUserId());
                            session.reduceAvailableSlots();
                            System.out.println("You have been successfully booked...");
                            System.out.println();
                        }
                        break;
                    }
                }
                break;
            } else if (userInput.equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry...");
                System.out.println();
            }
        }
    }

    public void attendLesson() {
        while (true) {
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
                            System.out.println("Thank you for attending this session! Hope to see you again...");
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
                                System.out.println();
                                break;
                            } else {
                                System.out.println("Write a review: ");
                                scanner.nextLine();
                                String userReview = scanner.nextLine();
                                session.getCustomerReviews().add(userReview);
                                System.out.println();

                                do {
                                    System.out.println("Please leave a rating: 1: Very dissatisfied, 2: Dissatisfied, 3: Ok, 4: Satisfied, 5: Very Satisfied");
                                    userChoice = scanner.next();
                                } while (!ratings.contains(userChoice));

                                session.getCustomerRatings().add(Float.parseFloat(userChoice));
                                System.out.println();

                            }
                            System.out.println("Thank you for your time, Goodbye...");
                            System.out.println();
                        } else {
                            System.out.println("Sorry, You are not booked for this session...");
                            System.out.println();
                        }
                        break;
                    }
                }
                break;
            } else if (getUserFitness().equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry...");
                System.out.println();
            }
        }
    }

    public void cancelBooking() {
        while (true) {
            System.out.println("Enter 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to cancel or 'back' to go back:");
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
                            session.getBookedCustomers().remove(cm.getUserId());
                            session.increaseAvailableSlots();
                            System.out.println("Lesson cancelled successfully...");
                            System.out.println();
                            System.out.println("Do you wish to book another lesson? Enter 'Yes' or 'No:");
                            scanner.nextLine();
                            String userChoice = scanner.nextLine();
                            if (userChoice.equalsIgnoreCase("no") || userChoice.equalsIgnoreCase("yes")) {
                                if (userChoice.equalsIgnoreCase("no")) {
                                    System.out.println("Sorry to see you go! Hope to see you again...");
                                    System.out.println();
                                } else {
                                    bookCustomer();
                                }
                            } else {
                                System.out.println("Sorry, Invalid entry...");
                            }
                        } else {
                            System.out.println("Sorry, You are not booked for this session...");
                            System.out.println();
                        }
                        break;
                    }
                }
                break;
            } else if (getUserFitness().equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry...");
                System.out.println();
            }
        }
    }

    public void printMonthlyReport() {
        while(true) {
            System.out.println("Please enter 'June' or 'July' for a monthly report or 'back' to go back: ");
            String userChoice = scanner.next();
            if (userChoice.equalsIgnoreCase("june") || userChoice.equalsIgnoreCase("july")) {
                if (userChoice.equalsIgnoreCase("june")) {
                    for (Session session : tt.getJuneSessions()) {
                        System.out.println("Fitness Type: " + session.getName() + " --- " + "Week: " + session.getWeek() + "\n" +
                                "-------------------------------------\n" +
                                "Booked Customers: " + session.getBookedCustomers().size() + " --- " + "Attended Customers: " + session.getAttendedCustomers() + "\n" +
                                "Average rating: " + Session.getAverageRating(session.getCustomerRatings()) + " --- " + "Available Slots: " + session.getAvailableSlots() + "\n" +
                                "Customer reviews: " + Session.printCustomerReviews(session.getCustomerReviews()));
                        System.out.println();
                    }
                }
            } else if (userChoice.equalsIgnoreCase("back")) {
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
}
