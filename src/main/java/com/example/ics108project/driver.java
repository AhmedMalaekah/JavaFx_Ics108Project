package com.example.ics108project;
import java.util.Date;


public class driver {
    public static void main(String[] args) {
        Event.addEvent("event1","workshop","java learning workshop",new Date(),"12PM","building 22",100);
        System.out.println(Event.getEvents().get(0).getTitle());
        Event.delEvent("event1");
        System.out.println(Event.getEvents());
    }
}
