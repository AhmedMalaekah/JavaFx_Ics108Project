package com.example.ics108project;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.text.*;

import Authentication.User;
import javafx.scene.control.Button;
public class Event implements Serializable {
    private String title;
    private String category;
    private String description;
    private Date date;

    private String time;
    private String location;
    private Ticket[] capacity;
    private int capacityNum;
//    private Button button;
    private User user;

    private static  ArrayList<Event> events = new ArrayList<>();


    public Event() {
        this.title = "noTitle";
        this.category = "noCategory";
        this.description = "noDescription";
        this.time = "noTime";
        this.location =" noLocation";
        this.capacity = null;
//        this.button = new Button("book");
    }


    public Event(String title, String category, String description, Date date, String time, String location, int capacityNum,User user) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacityNum = capacityNum;
        this.capacity = new Ticket[capacityNum];
//        this.button = new Button("book");
        this.user = user;

    }

    public static void createEvent(String title, String category, String description, Date date, String time, String location, int capacityNum, User user){
        Event event = new Event(title, category, description, date, time, location, capacityNum, user);
        Event.getEvents().add(event);
    }
    // may we make several methods and each one of them edit specific thing
    public static void addEvent(String title, String category, String description, Date date, String time, String location, int capacity,User user){
        events.add(new Event(title,category,description,date,time,location,capacity,user));
    }
    public  static void addEvent(Event event){
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

//    public Button getButton() {
//        return button;
//    }

//    public void setButton(Button button) {
//        this.button = button;
//    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        addEvent("event1","workshop","java learning workshopjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj",new SimpleDateFormat("dd/MM/yyyy").parse("02/04/2024"),"11PM","building 22", 150,new User("Ahmed","test",true));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
        addEvent("event2","workshopJavas","java learning workshop",new Date(),"12PM","building 22", 150,new User("Yousef","123",false));
    }
}
