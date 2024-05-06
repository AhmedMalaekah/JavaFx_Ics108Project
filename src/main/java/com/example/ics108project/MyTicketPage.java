package com.example.ics108project;

import com.example.ics108project.User;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyTicketPage extends Application {
    User user;
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane centerContainer = new BorderPane();

        for (int i = 0; i < Event.getEvents().size(); i++) {
            if (Event.getEvents().get(i).getTickets().contains(user)){
                System.out.println(Event.getEvents().get(1));
            }

        }

    }
}




//for (int i = 0; i < Event.getEvents().size(); i++) {
//        for (int j = 0; j < Event.getEvents().get(i).getCapacity().size(); j++) {
//        if (Event.getEvents().get(i).getCapacity().get(j).getUser().equals(currentUser)){
//        System.out.println("Event Founded");
//                    System.out.println(Event.getEvents().get(i).toString());
//        }
//        }
//
//        }
