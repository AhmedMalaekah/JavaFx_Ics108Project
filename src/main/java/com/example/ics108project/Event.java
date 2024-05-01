package com.example.ics108project;
import Authentication.User;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import static java.lang.System.out;
public class Event implements Serializable {
    private String title;
    private String category;
    private String description;
    private LocalDate date;

    private String time;
    private String location;
    private ArrayList<Ticket> capacity;
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


    public Event(String title, String category, String description, LocalDate date, String time, String location, int capacityNum,User user) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacityNum = capacityNum;
//        this.capacity = new Ticket[capacityNum];
//        this.button = new Button("book");
        this.user = user;
    }


    public Event(String title, String category, String description, LocalDate date, String time, String location, int capacityNum) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacityNum = capacityNum;
        this.capacity = new ArrayList<Ticket>(capacityNum);

    }

    public static void createEvent(String title, String category, String description, LocalDate date, String time, String location, int capacityNum){
        Event newevent = new Event(title, category, description, date, time, location, capacityNum);
        Event.getEvents().add(newevent);
    }
    // may we make several methods and each one of them edit specific thing


    public void addTicket(User user){

        this.capacity.add(new Ticket(user));

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
        if (date.isAfter(LocalDate.now())){
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
        return date.toString();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCapacityNum() {
        return capacityNum;
    }

    public void setCapacityNum(int capacityNum) {
        this.capacityNum = capacityNum;
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

    public ArrayList<Ticket> getCapacity() {
        return this.capacity;
    }


    public void setCapacity(int capacityNum) {
        this.capacityNum = capacityNum;
    }
    public static void loadEvents() throws ParseException {
        // when you add the peanel for adding event you can deldte these addevents
        try {
            FileInputStream fileStream  = new FileInputStream(new File("Events.txt"));
            ObjectInputStream os  = new ObjectInputStream(fileStream);
            Object eventObject = null;
            while((eventObject = os.readObject()) != null) {
                Event castObject = (Event) eventObject;
                events.add(castObject);
            }
            os.close();
        }  catch(Exception ex) {
            if(ex instanceof EOFException) {
                out.println("End of events file reached!");
                out.println("Total Events found is: " + Event.getEvents().size());
            } else if(ex instanceof FileNotFoundException) {
                out.println("Events File not found! \n This is your default Events");
                createEvent("event1","workshopJavas","java learning workshop", LocalDate.now(),"12PM","building 22", 1);
                createEvent("event2","workshopJavas","java learning workshop",LocalDate.now(),"12PM","building 22", 1);
                createEvent("event3","workshopJavas","java learning workshop",LocalDate.of(2024,5,2),"12PM","building 22", 1);
                createEvent("event4","workshopJavas","java learning workshop",LocalDate.of(2024,4,5),"12PM","building 22", 1);
            }
        }

    }
}
