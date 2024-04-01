package com.example.ics108project;
import Authentication.User;

import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;


public class driver {
    public static void main(String[] args) {
//        Event.addEvent("event1","workshop","java learning workshop",new Date(),"12PM","building 22",100);
//        System.out.println(Event.getEvents().get(0).getTitle());
//        Event e2 = new Event("event2","workshop","java learning workshop",new Date(),"11PM","building 22",150);
//        Event.delEvent("event1");
//        System.out.println(Event.getEvents());
        User ahmed = new User("Ahmed","test",true);
        System.out.println(User.getPasswords());
        LoginPage.main(args);

    }
}
