package com.example.ics108project;
import Authentication.User;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;


public class driver {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sdf.parse("05/04/2024");
//        Event.addEvent("event1","workshop","java learning workshop",new Date(),"12PM","building 22",100);
//        System.out.println(Event.getEvents().get(0).getTitle());
        Event e2 = new Event("event2","workshop","java learning workshop",date1,"11PM","building 22",150);
        System.out.println(e2.isUpcoming());

//        Event.delEvent("event1");
//        System.out.println(Event.getEvents());
        User ahmed = new User("Ahmed","test",true);
        System.out.println(User.getPasswords());
        LoginPage.main(args);

    }
}
