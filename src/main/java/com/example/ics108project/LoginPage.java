package com.example.ics108project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class LoginPage extends Application {


    @Override
    public void start(Stage stage){
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        pane.setPrefSize(700,500);
        TextField text1 = new TextField();
        Label headerLabel = new Label("Login Screen");
        headerLabel.setLayoutX(300);
        headerLabel.setLayoutY(15);
        headerLabel.setScaleY(3);
        headerLabel.setScaleX(3);
        pane.getChildren().add(headerLabel);
        Label loginMassage = new Label();
        loginMassage.setLayoutX(275);
        loginMassage.setLayoutY(50);
        loginMassage.setScaleY(1.5);
        loginMassage.setScaleX(1.5);
        pane.getChildren().add(loginMassage);
        Label userFieldLabel = new Label("Username");
        userFieldLabel.setLayoutX(145);
        userFieldLabel.setLayoutY(225);
        userFieldLabel.setScaleY(1.5);
        userFieldLabel.setScaleX(1.5);
        pane.getChildren().add(userFieldLabel);
        TextField userField = new TextField();
        userField.setLayoutX(100);
        userField.setLayoutY(250);
        Label passFieldLabel = new Label("Password");
        passFieldLabel.setLayoutX(500);
        passFieldLabel.setLayoutY(225);
        passFieldLabel.setScaleY(1.5);
        passFieldLabel.setScaleX(1.5);
        pane.getChildren().add(passFieldLabel);
        TextField passField = new TextField();
        passField.setLayoutX(450);
        passField.setLayoutY(250);
        Button loginButton = new Button();
        loginButton.setPrefSize(75,35);
        loginButton.setLayoutX(300);
        loginButton.setLayoutY(400);
        loginButton.setText("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginMassage.setText("Welcome "+ userField.getText()
                );
            }
        });
        pane.getChildren().add(userField);
        pane.getChildren().add(passField);
        pane.getChildren().add(loginButton);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
