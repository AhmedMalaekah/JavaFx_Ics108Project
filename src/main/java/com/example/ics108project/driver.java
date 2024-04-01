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
        Event e1 = new Event("event2","workshop","java learning workshop",date1,"11PM","building 22",150);
        User ahmed = new User("Ahmed","test",true);
        User yosef = new User("yosef","123",true);
        LoginPage.main(args);

    }
}
