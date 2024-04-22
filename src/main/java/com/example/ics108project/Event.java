package com.example.ics108project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.*;
import javafx.scene.control.Button;
public class Event {
    private String title;
    private String category;
    private String description;
    private Date date;

    private String time;
    private String location;
    private Ticket[] capacity;
    private int capacityNum;
    private Button button;

    private static  ArrayList<Event> events = new ArrayList<>();


    public Event() {
        this.title = "noTitle";
        this.category = "noCategory";
        this.description = "noDescription";
        this.time = "noTime";
        this.location =" noLocation";
        this.capacity = null;
        this.button = new Button("book");
    }


    public Event(String title, String category, String description, Date date, String time, String location, int capacityNum) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacityNum = capacityNum;
        this.capacity = new Ticket[capacityNum];
        this.button = new Button("book");
    }

    public static void createEvent(String title, String category, String description, Date date, String time, String location, int capacityNum){
        Event event = new Event(title, category, description, date, time, location, capacityNum);
        events.add(event);
    }
    public static ArrayList<Event> getEvents() {
        return events;
    }
    public static void delEvent(Event event){
        events.remove(event);
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getDateString() {
        return DateFormat.getDateInstance().format(this.date.getTime());
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

    public int getCapacityNum() {
        return capacityNum;
    }

    public void setCapacityNum(int capacityNum) {
        this.capacityNum = capacityNum;
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
    public void setCapacity(int capacityNum) {
        this.capacityNum = capacityNum;
    }
    public static void loadEvents() throws ParseException {
        Event e1 = new Event("event1","workshop","java learning workshop",new SimpleDateFormat("dd/MM/yyyy").parse("02/04/2024"),"11PM","building 22", 150);
        Event e2 = new Event("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150);
    }
}
