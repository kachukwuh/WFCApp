package com.example.wfcapp;
import java.util.Scanner;

public class WFCApp {

    public static void main(String[] args) {
        Booking bk = new Booking();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        System.out.println("Hello Welcome to the Weekend Fitness Club!");
        while(true) {
            System.out.println("""
                Please enter:\s
                1 to book a lesson
                2 to attend a lesson
                3 to cancel/change a lesson
                4 to print a monthly lesson report
                5 to print a champion fitness type report
                6 to exit""");

            System.out.println();
            userInput = scanner.next();


            if (userInput.equalsIgnoreCase("1")) {
                bk.bookCustomer();
                System.out.println();
            } else if (userInput.equalsIgnoreCase("2")) {
                bk.attendLesson();
                System.out.println();
            } else if (userInput.equalsIgnoreCase("3")) {
                bk.cancelBooking();
                System.out.println();
            } else if (userInput.equalsIgnoreCase("4")) {
                bk.printMonthlyReport();
            } else if (userInput.equalsIgnoreCase("6")) {
                System.out.println("Thank you for coming! Goodbye...");
                break;
            }
        }
    }
}
