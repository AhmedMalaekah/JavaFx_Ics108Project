package com.example.ics108project;
import Authentication.User;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


public class driver {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sdf.parse("02/04/2024");
        Date date2 = new Date();
        Event e1 = new Event("event1","workshop","java learning workshop",date1,"11PM","building 22", 150);
        Event e2 = new Event("event2","workshopJavas","java learning workshop",date2,"12PM","building 22", 150);
        System.out.println(e2.getDateString());

        User ahmed = new User("Ahmed","test",true);
        User yosef = new User("yosef","123",false);
//        LoginPage.main(args);
        AdminEvents.main(args);

    }
}
