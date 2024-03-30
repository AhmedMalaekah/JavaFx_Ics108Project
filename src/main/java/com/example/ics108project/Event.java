package com.example.ics108project;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String title;
    private String category;
    private String description;
    private Date date;
    private String time;
    private String location;
    private int capacity;

    private Ticket[] tickets;

    private static  ArrayList<Event> events = new ArrayList<>();


    public Event() {
    }

    public Event(String title, String category, String description, Date date, String time, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
        this.tickets = new Ticket[capacity];
    }
    public static void addEvent(String title, String category, String description, Date date, String time, String location, int capacity){
        events.add(new Event(title,category,description,date,time,location,capacity));
    }
    public  static void addEvent(Event event){
        events.add(new Event(event.title,event.category,event.description,event.date,event.time,event.location,event.capacity));
    }
    public static ArrayList<Event> getEvents() {
        return events;
    }
    public static void delEvent(String eventName){
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).title.equals(eventName)){
                events.remove(i);
            }
        }


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
