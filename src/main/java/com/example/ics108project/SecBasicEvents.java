package com.example.ics108project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SecBasicEvents extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SecBasicEvents.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        SecBasicEventsController test = new SecBasicEventsController();
//        test.duplicateGroup();

        stage.setTitle("KFUPM Events Booking System");
        stage.setScene(scene);
        stage.show();


    }
}
