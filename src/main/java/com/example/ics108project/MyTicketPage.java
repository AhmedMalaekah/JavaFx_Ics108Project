package com.example.ics108project;

import Authentication.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MyTicketPage extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        for (int i = 0; i < Event.getEvents().size(); i++) {
//            if Event.getEvents().get(i).getUser() ==

        }

    }
}
