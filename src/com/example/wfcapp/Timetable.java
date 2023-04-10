package com.example.wfcapp;
import java.util.ArrayList;
import java.util.List;


public class Timetable {
    private final int numOfWeeks;
    private final ArrayList<Lesson> lessons;
    private final ArrayList<Session> sessions;
    private final ArrayList<Session> juneSessions;
    private final ArrayList<Session> julySessions;

    public Timetable() {
        this.numOfWeeks = 8;
        this.lessons = new ArrayList<>(List.of(
                new Lesson("Yoga", "09:00am - 10:00am", "Saturday", 12.00f),
                new Lesson("Spin", "12:00pm - 01:00pm", "Saturday", 11.50f),
                new Lesson("Zumba", "09:00am - 10:00am", "Sunday", 11.75f),
                new Lesson("Body-Sculpt", "12:00pm - 01:00pm", "Sunday", 11.00f)
                ));
        this.sessions = new ArrayList<>();
        for (int i = 1; i <= numOfWeeks; i++) {
            for (Lesson lesson : lessons) {
                this.sessions.add(new Session(lesson.getName(),lesson.getName() +"-"+ i, new ArrayList<>(), lesson.getPrice(), 5, 0, new ArrayList<>(), new ArrayList<>(), i));
            }
        }
        this.juneSessions = new ArrayList<>();
        this.julySessions = new ArrayList<>();

        for (Session session : this.sessions) {
            ArrayList<Integer> juneWeeks = new ArrayList<>(List.of(1,2,3,4));
            if (juneWeeks.contains(session.getWeek())) {juneSessions.add(session);} else {julySessions.add(session);}
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
    public ArrayList<Lesson> getLessons() {return this.lessons;}
    public ArrayList<Session> getJuneSessions() {return this.juneSessions;}
    public ArrayList<Session> getJulySessions() {return this.julySessions;}

    public String toString(Lesson lesson) {
        return lesson.getName() + "," + " " + lesson.getDay() + "," + " " + lesson.getLessonTime() + " - " + "£" + lesson.getPrice();
    }
}