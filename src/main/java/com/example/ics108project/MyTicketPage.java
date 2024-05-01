package com.example.ics108project;

import Authentication.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyTicketPage extends Application {
    User user;
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane centerContainer = new BorderPane();

        for (int i = 0; i < Event.getEvents().size(); i++) {
            if (Event.getEvents().get(i).getCapacity().contains(user)){
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
