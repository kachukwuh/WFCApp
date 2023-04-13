package com.example.wfcapp;
import java.util.*;

public class Booking {
    private final ArrayList<String> validDays;
    private String userFitness;
    private String userWeek;
    private String userFitnessId;
    private final ArrayList<String> fitnessTypes;
    private final ArrayList<String> fitnessWeeks;
    private Map<String, Float> fitnessMap;

    public Booking() {
        this.validDays = new ArrayList<>(List.of("saturday", "sunday"));
        this.fitnessTypes = new ArrayList<>(List.of("yoga", "spin", "zumba", "body-sculpt"));
        this.fitnessWeeks = new ArrayList<>(List.of("1", "2", "3", "4", "5", "6", "7", "8"));
        this.userFitness = "";
        this.userWeek = "";
        this.userFitnessId = "";
    }

    Scanner scanner = new Scanner(System.in);
    Timetable timetable = new Timetable();
    Customer customer = new Customer("", "");


    public void bookCustomer() {
        while (true) {
            System.out.println("Enter 'Saturday', 'Sunday', 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to view timetable or 'back' to go back:");
            String userInput = scanner.next();
            if (getValidDays().contains(userInput.toLowerCase()) || getFitnessTypes().contains(userInput.toLowerCase())) {
                timetable.displayTimetable(userInput);
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
                customer.setFirstName(scanner.next().toLowerCase());
                System.out.println("Please, enter your Last Name: ");
                customer.setLastName(scanner.next().toLowerCase());

                for (Session session : timetable.getSessions()) {
                    if (userInput.equalsIgnoreCase(session.getSessionId())) {
                        if (session.getAvailableSlots() < 1) {
                            System.out.println("Sorry, This session is fully booked.");
                            System.out.println();
                        } else if (session.getBookedCustomers().contains(customer.getUserId())) {
                            System.out.println("Sorry, You have already booked this session.");
                            System.out.println();
                        } else {
                            session.addToBookedCustomers(customer.getUserId());
                            session.reduceAvailableSlots();
                            System.out.println("Booking Successful.");
                            System.out.println();
                        }
                        break;
                    }
                }
                break;
            } else if (userInput.equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry.");
                System.out.println();
            }
        }
    }

    public void attendLesson() {
        while (true) {
            System.out.println("Please, enter your First Name: ");
            customer.setFirstName(scanner.next().toLowerCase());
            System.out.println("Please, enter your Last Name: ");
            customer.setLastName(scanner.next().toLowerCase());

            ArrayList<String> customerBookedSessions = new ArrayList<>();
            for (Session session : timetable.getSessions()) {
                if (session.getBookedCustomers().contains(customer.getUserId())) {
                    customerBookedSessions.add("You booked a " + session.getName() + " class for week " + session.getWeek());
                }
            }

            if (customerBookedSessions.size() < 1) {
                System.out.println("Sorry, We found no record of any booked session for " + customer.getFirstName().toUpperCase() + " " + customer.getLastName().toUpperCase());
                System.out.println();
                break;
            } else {
                System.out.println("We found " + customerBookedSessions.size() + " booked session(s) for " + customer.getFirstName().toUpperCase() + " " + customer.getLastName().toUpperCase());
                System.out.println();
                int counter = 1;
                for (String customerBookedSession : customerBookedSessions) {
                    System.out.println(counter + "." + " " + customerBookedSession);
                    counter++;
                }
                System.out.println();
            }

            System.out.println("Enter 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to attend or 'back' to go back:");
            setUserFitness(scanner.next());
            if (getFitnessTypes().contains(getUserFitness().toLowerCase())) {
                do {
                    System.out.println("Enter 1, 2, 3, 4, 5, 6, 7, 8 to select a week.");
                    setUserWeek(scanner.next());
                } while (!getFitnessWeeks().contains(getUserWeek()));

                setUserFitnessId();

                for (Session session : timetable.getSessions()) {
                    if (getUserFitnessId().equalsIgnoreCase(session.getSessionId())) {
                        if (session.getBookedCustomers().contains(customer.getUserId())) {
                            session.increaseAttendedCustomers();
                            session.increaseAvailableSlots();
                            session.getBookedCustomers().remove(customer.getUserId());
                            System.out.println("Thank you for attending this session! Hope to see you again.");
                            System.out.println();

                            String userChoice;
                            ArrayList<String> choices = new ArrayList<>(List.of("yes", "no"));
                            ArrayList<String> ratings = new ArrayList<>(List.of("1", "2", "3", "4", "5"));
                            do {
                                System.out.println("Do you wish to write a review and rate our services? Enter 'Yes' or 'No:'");
                                userChoice = scanner.next();
                            } while (!choices.contains(userChoice.toLowerCase()));

                            if (userChoice.equalsIgnoreCase("no")) {
                                System.out.println("Thank you for your time, Goodbye.");
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
                            System.out.println("Thank you for your time, Goodbye.");
                            System.out.println();
                        } else {
                            System.out.println("Sorry, You are not booked for this session.");
                            System.out.println();
                        }
                        break;
                    }
                }
                break;
            } else if (getUserFitness().equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry.");
                System.out.println();
            }
        }
    }

    public void cancelBooking() {
        while (true) {
            System.out.println("Please, enter your First Name: ");
            customer.setFirstName(scanner.next().toLowerCase());
            System.out.println("Please, enter your Last Name: ");
            customer.setLastName(scanner.next().toLowerCase());

            ArrayList<String> customerBookedSessions = new ArrayList<>();
            for (Session session : timetable.getSessions()) {
                if (session.getBookedCustomers().contains(customer.getUserId())) {
                    customerBookedSessions.add("You booked a " + session.getName() + " class for week " + session.getWeek());
                }
            }

            if (customerBookedSessions.size() < 1) {
                System.out.println("Sorry, We found no record of any booked session for " + customer.getFirstName().toUpperCase() + " " + customer.getLastName().toUpperCase());
                System.out.println();
                break;
            } else {
                System.out.println("We found " + customerBookedSessions.size() + " booked session(s) for " + customer.getFirstName().toUpperCase() + " " + customer.getLastName().toUpperCase());
                System.out.println();
                int counter = 1;
                for (String customerBookedSession : customerBookedSessions) {
                    System.out.println(counter + "." + " " + customerBookedSession);
                    counter++;
                }
                System.out.println();
            }

            System.out.println("Enter 'Yoga', 'Spin', 'Zumba', 'Body-Sculpt' to cancel or 'back' to go back:");
            setUserFitness(scanner.next());
            if (getFitnessTypes().contains(getUserFitness().toLowerCase())) {
                do {
                    System.out.println("Enter 1, 2, 3, 4, 5, 6, 7, 8 to select a week.");
                    setUserWeek(scanner.next());
                } while (!getFitnessWeeks().contains(getUserWeek()));

                setUserFitnessId();

                for (Session session : timetable.getSessions()) {
                    if (getUserFitnessId().equalsIgnoreCase(session.getSessionId())) {
                        if (session.getBookedCustomers().contains(customer.getUserId())) {
                            session.getBookedCustomers().remove(customer.getUserId());
                            session.increaseAvailableSlots();
                            System.out.println("Booking cancelled successfully.");
                            System.out.println();
                            System.out.println("Do you wish to book another lesson? Enter 'Yes' or 'No:");
                            scanner.nextLine();
                            String userChoice = scanner.nextLine();
                            if (userChoice.equalsIgnoreCase("no") || userChoice.equalsIgnoreCase("yes")) {
                                if (userChoice.equalsIgnoreCase("no")) {
                                    System.out.println("Sorry to see you go! Come back soon.");
                                    System.out.println();
                                } else {
                                    bookCustomer();
                                }
                            } else {
                                System.out.println("Sorry, Invalid entry.");
                            }
                        } else {
                            System.out.println("Sorry, You are not booked for this session.");
                            System.out.println();
                        }
                        break;
                    }
                }
                break;
            } else if (getUserFitness().equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry.");
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
                    for (Session session : timetable.getJuneSessions()) {
                        reportString(session);
                    }
                } else {
                    for (Session session : timetable.getJulySessions()) {
                        reportString(session);
                    }
                }
            } else if (userChoice.equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry.");
            }
        }
    }

    public void printMonthlyChampion() {
        while(true) {
            System.out.println("Please enter 'June' or 'July' for a monthly report or 'back' to go back: ");
            String userChoice = scanner.next();
            if (userChoice.equalsIgnoreCase("june") || userChoice.equalsIgnoreCase("july")) {
                if (userChoice.equalsIgnoreCase("june")) {
                    reportChampion(timetable.getJuneSessions());
                } else {
                    reportChampion(timetable.getJulySessions());
                }
            } else if (userChoice.equalsIgnoreCase("back")) {
                break;
            } else {
                System.out.println("Sorry, Invalid entry.");
            }

            for (Map.Entry<String, Float> set : fitnessMap.entrySet()) {
                System.out.println("Fitness Type: " + set.getKey() + " --- " + "Total income generated: £" + set.getValue());
            }
            System.out.println();
        }
    }

    public void reportChampion(ArrayList<Session> sessions) {
        ArrayList<String> fitnessTypes = new ArrayList<>(List.of("Yoga", "Spin", "Zumba", "Body-Sculpt"));
        this.fitnessMap = new HashMap<>(Map.of("Yoga", 0f, "Spin", 0f, "Zumba", 0f, "Body-Sculpt", 0f));

        for (Session session : sessions) {
            for (String fitness : fitnessTypes) {
                if (fitness.equalsIgnoreCase(session.getName())) {
                    for (Map.Entry<String, Float> set : fitnessMap.entrySet()) {
                        if (set.getKey().equalsIgnoreCase(fitness)) {
                            fitnessMap.put(fitness, fitnessMap.get(fitness) + session.getAttendedCustomers());
                        }
                    }
                }
            }
        }

        for (Map.Entry<String, Float> set : fitnessMap.entrySet()) {
            for (Session session : sessions) {
                if (set.getKey().equalsIgnoreCase(session.getName())) {
                    fitnessMap.put(set.getKey(), fitnessMap.get(set.getKey()) * session.getPrice());
                    break;
                }
            }
        }
    }

    public void reportString(Session session) {
        System.out.println("Fitness Type: " + session.getName() + " --- " + "Week: " + session.getWeek() + "\n" +
                "-------------------------------------\n" +
                "Booked Customers: " + session.getBookedCustomers().size() + " --- " + "Attended Customers: " + session.getAttendedCustomers() + "\n" +
                "Average rating: " + Session.getAverageRating(session.getCustomerRatings()) + " --- " + "Available Slots: " + session.getAvailableSlots() + "\n" +
                "Total income generated: £" + Session.totalIncomeGenerated(session.getPrice(), session.getAttendedCustomers()) + "\n" +
                "Customer reviews: " + Session.printCustomerReviews(session.getCustomerReviews()));
        System.out.println();
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