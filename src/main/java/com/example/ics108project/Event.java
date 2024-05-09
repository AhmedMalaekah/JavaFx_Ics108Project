package com.example.ics108project;
import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.System.out;
public class Event implements Serializable {
    private String title;
    private String category;
    private String description;
    private LocalDate date;
    private String time;
    private String location;
    private ArrayList<Ticket> tickets;
    private ArrayList<Integer> ticketsNum;
    private int capacityNum;
    private User user;

    private static  ArrayList<Event> events = new ArrayList<>();

    public Event(String title, String category, String description, LocalDate date, String time, String location, int capacityNum,User user) {
        this.setTitle(title);
        this.setCategory(category);
        this.setDescription(description);
        this.setDate(date);
        this.setTime(time);
        this.setLocation(location);
        this.setCapacityNum(capacityNum);
        this.tickets = new ArrayList<Ticket>(capacityNum);
        this.ticketsNum = new ArrayList<Integer>();
        this.setTicketsNum();
        this.user = user;
    }

    public void editEvent(String title, String category, String description, LocalDate date, String time, String location ,User user){
        this.setTitle(title);
        this.setCategory(category);
        this.setDescription(description);
        this.setDate(date);
        this.setTime(time);
        this.setLocation(location);
        this.setUser(user);
    }

    public static void createEvent(String title, String category, String description, LocalDate date, String time, String location, int capacityNum, User user){
        Event newevent = new Event(title, category, description, date, time, location, capacityNum, user);
        Event.getEvents().add(newevent);
    }
    public void addTicket(User user){
        if(this.getNumTicketsAvailable() > 0) {
            this.tickets.add(new Ticket(user.getUsername(), this.getTicketNum()));
        }
    }

    public void delTicket(Integer seatNum){
        for (int i = 0; i < this.tickets.size(); i++) {
            if (tickets.get(i).getSeatNum().equals(seatNum)){
                this.ticketsNum.add(seatNum);
                this.tickets.remove(tickets.get(i));
            }

        }
    }

    public void setTicketsNum(){
        for(int i =1; i<=this.capacityNum; i++){
            this.ticketsNum.add(i);
        }
    }

    public Integer getTicketNum(){
        Integer num = ticketsNum.getFirst();
        ticketsNum.remove(num);
        return num;
    }

    public void clearTickets(){
        this.tickets.clear();
    }

    public boolean bookedByUser(User user){
        for (Ticket ticket : tickets) {
            if (ticket.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
    public static ArrayList<Event> getEvents() {
        return events;
    }
    public static void delEvent(Event event){
        events.remove(event);
    }

    public boolean isUpcoming(){
        if (date.isAfter(LocalDate.now()) || date.equals(LocalDate.now())){
            return true;
        }
        else return false;
    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title){
        if(!title.isEmpty()){
            this.title = title;
        }
        else{
            this.title = "no title";
        }
    }

    public String getDateString() {
        return date.toString();
    }

    public String getCategory() {return category;}
    public void setCategory(String category){
        if(!category.isEmpty()){
            this.category = category;
        }
        else{
            this.category = "";
        }
    }
    public String getDescription() {return description;}
    public void setDescription(String description){
        if(!description.isEmpty()){
            this.description = description;
        }
        else{
            this.description = "empty description";
        }
    }
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date){
        if(date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now())){
            this.date = date;
        }
        else{
            this.date = LocalDate.now();
        }
    }


    public int getCapacityNum() {
        return capacityNum;
    }
    public void setCapacityNum(int capacityNum){
        if(capacityNum > 0){
            this.capacityNum = capacityNum;
        }
        else{
            this.capacityNum = 1;
        }
    }

    public int getNumTicketsAvailable(){
        return capacityNum - tickets.size();
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time){
        if(!time.isEmpty()){
            this.time = time;
        }
        else{
            this.time = "TBA";
        }
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location){
        if(!location.isEmpty()){
            this.location = location;
        }
        else{
            this.location = "TBA";
        }
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
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
                out.print("");
            } else if(ex instanceof FileNotFoundException) {
                out.println("Events File not found! \n This is your default Events");
                User user1 = User.getUsers().getFirst();
                createEvent("GitHub","workshop","GitHub learning workshop for continues integration", LocalDate.now(),"8:00PM","building 22", 1, user1);
                createEvent("Toastmaster","workshopJavas","Learn how to express yourself with the world champions",LocalDate.now(),"7:00PM","building 54", 1, user1);
                createEvent("Competitive Programming","competition","Compete in programming against experts",LocalDate.of(2024,5,2),"6:00PM","building 22", 1, user1);
                createEvent("Graduation Party","party","Have fun with family and friends",LocalDate.of(2024,4,5),"5:00PM","KFUPM Stadium", 1, user1);
            }
        }

    }
}
