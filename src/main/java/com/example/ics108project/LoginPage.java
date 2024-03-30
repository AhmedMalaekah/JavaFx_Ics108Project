package com.example.ics108project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class LoginPage extends Application {


    @Override
    public void start(Stage stage){
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane);
        Label headerLabel = new Label("Login Screen");
        headerLabel.setScaleY(1.5);
        headerLabel.setScaleX(1.5);
        Label loginMassage = new Label();
        Label userFieldLabel = new Label("Username");
        TextField userField = new TextField();
        Label passFieldLabel = new Label("Password");
        TextField passField = new TextField();
        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginMassage.setText("Welcome " + userField.getText()
                );
            }
        });
        gridPane.add(headerLabel,3,1);
        gridPane.add(loginMassage,3,2);
        gridPane.add(userFieldLabel,2,4);
        gridPane.add(passFieldLabel,3,4);
        gridPane.add(userField,2,5);
        gridPane.add(passField,3,5);
        gridPane.add(loginButton,5,5);
        Insets gridPadding = new Insets(10,10,10,10);
        gridPane.setPadding(gridPadding);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
