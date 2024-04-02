package com.example.ics108project;

import Authentication.User;
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

import java.util.ArrayList;

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
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");


        loginButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean userFound = false;

                int index = User.getUsernames().indexOf(userField.getText());
                if (index != -1){
                    userFound = true;

                }
                else index =0;
                User user = User.getUsers().get(index);
                {
                    if (user.getUsername().equals(userField.getText())&& user.checkPassword(passField.getText())&& user.isAdmin()&& userFound){
                        System.out.println("login done successfully");
                        System.out.println("I am an admin");
                    }
                    else if (user.getUsername().equals(userField.getText())&& user.checkPassword(passField.getText())&& !user.isAdmin()&& userFound){
                        System.out.println("login done successfully");
                        System.out.println("I am an basic user");
                    }
                    else {
                        System.out.println("Wrong credintials");
                    }
                }
            }
        });
        gridPane.add(headerLabel,3,1);
        gridPane.add(loginMassage,3,2);
        gridPane.add(userFieldLabel,2,4);
        gridPane.add(passFieldLabel,3,4);
        gridPane.add(userField,2,5);
        gridPane.add(passField,3,5);
        gridPane.add(loginButton,5,5);
        gridPane.add(registerButton,6,5);
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
