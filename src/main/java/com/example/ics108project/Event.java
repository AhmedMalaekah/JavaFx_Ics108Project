package com.example.ics108project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String title;
    private String category;
    private String description;
    private Date date;

    private String dateString;
    private String time;
    private String location;
    private Ticket[] capacity;
    private int capacityNum;


    private static  ArrayList<Event> events = new ArrayList<>();


    public Event() {
        this.title = "noTitle";
        this.category = "noCategory";
        this.description = "noDescription";
        this.dateString = "noDate";
        this.time = "noTime";
        this.location =" noLocation";
        this.capacity = null;
    }

    public Event(String title, String category, String description, String date, String time, String location, int capacity) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.dateString = date;
        this.time = time;
        this.location = location;
        this.capacityNum = capacity;
        Event.getEvents().add(this);
    }
    // may we make several methods and each one of them edit specific thing
    public static void addEvent(String title, String category, String description, String date, String time, String location, int capacity){
        events.add(new Event(title,category,description,date,time,location,capacity));
    }
    public  static void addEvent(Event event){
        events.add(new Event(event.title,event.category,event.description,event.dateString,event.time,event.location,event.capacityNum));
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
    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
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

    public void setDate(String date)  {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.date = sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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
    public void setCapacity(int capacity) {
        this.capacityNum = capacityNum;
    }
}
