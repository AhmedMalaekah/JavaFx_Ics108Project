package com.example.ics108project;
import Authentication.User;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.scene.control.Button;
import javafx.application.Platform;

public class Driver {
    static void initJfxRuntime() {
        Platform.startup(() -> {});
    }
    public static void main(String[] args) throws ParseException  {
        initJfxRuntime(); // important to have
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = sdf.parse("02/04/2024");
        Date date2 = new Date();
        Event e1 = new Event("event1","workshop","java learning workshop",date1,"11PM","building 22", 150);
        Event e2 = new Event("event2","workshopJavas","java learning workshop",date2,"12PM","building 22", 150);
//        System.out.println(e2.getDateString());
        System.out.println(Event.getEvents());
        User ahmed = new User("Ahmed","test",true);
        User yosef = new User("yosef","123",false);
        LoginPage.main(args);
//        AdminEvents.main(args);
//        BasicEvents.main(args);

    }
}
