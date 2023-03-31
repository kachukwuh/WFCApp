package com.example.wfcapp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Timetable {
    private final int numOfWeeks;
    private ArrayList<Lesson> lessons;
    private ArrayList<Session> sessions;

    public Timetable() {
        this.numOfWeeks = 8;
        this.lessons = new ArrayList<>(List.of(
                new Lesson("Yoga", "09:00am - 10:00am", "Saturday", 12.00),
                new Lesson("Spin", "12:00pm - 01:00pm", "Saturday", 11.50),
                new Lesson("Zumba", "09:00am - 10:00am", "Sunday", 11.75),
                new Lesson("Body-Sculpt", "12:00pm - 01:00pm", "Sunday", 11.00)
                ));
        this.sessions = new ArrayList<>();
    }

    public void createSessions() {
        for (int i = 1; i <= numOfWeeks; i++) {
            for (Lesson lesson : lessons) {
                this.sessions.add(new Session(lesson.getName() + i, new ArrayList<>(), lesson.getPrice(), 5, 0, new ArrayList<>(), new ArrayList<>()));
            }
        }
    }

    public void displayTimetable(String userInput) {
        for (int i = 1; i <= numOfWeeks; i++) {
            System.out.println("------ Week " + i + " ------");
            for (Lesson lesson : lessons) {
                if (userInput.equalsIgnoreCase(lesson.getName()) || userInput.equalsIgnoreCase(lesson.getDay())) {
                    System.out.println(toString(lesson));
                }
            }
            System.out.println();
        }
    }

    public ArrayList<Session> getSessions() {return this.sessions;}

    public String toString(Lesson lesson) {
        return lesson.getName() + "," + " " + lesson.getDay() + "," + " " + lesson.getLessonTime() + " - " + "£" + lesson.getPrice();
    }

    public static void main(String[] args) {
        Timetable tt = new Timetable();
        tt.createSessions();
        for (Session session : tt.sessions) {
            System.out.println(session.getFitnessId() + " " + session.getPrice() + " " + "total: " + (session.getPrice() * session.getAttendedCustomers()));
            System.out.println();
        }
//        tt.displayTimetable("sunday");
    }
}