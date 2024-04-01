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
    private Ticket[] capacity;


    private static  ArrayList<Event> events = new ArrayList<>();


    public Event() {
        this.title = "noTitle";
        this.category = "noCategory";
        this.description = "noDescription";
        this.date = null;
        this.time = "noTime";
        this.location =" noLocation";
        this.capacity = null;
    }

    public Event(String title, String category, String description, Date date, String time, String location, Ticket[] capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
    }
    // may we make several methods and each one of them edit specific thing
    public static void addEvent(String title, String category, String description, Date date, String time, String location, Ticket[] capacity){
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
    public  boolean isUpcoming(){
        if (date.after(new Date())){
            return true;
        }
        else return false;
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

    public Ticket[] getCapacity() {
        return capacity;
    }

    public void setCapacity(Ticket[] capacity) {
        this.capacity = capacity;
    }
}
