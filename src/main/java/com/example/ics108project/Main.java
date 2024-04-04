package com.example.ics108project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Authentication.User;

public class Main extends Application {

    public static final double winWidth = 800;
    public static final double winHeight = 600;
    public static User currentUser;
    @Override
    public void start(Stage applicationStage) {

        Scene currentScene;
        currentUser = loginPage(applicationStage);

        currentScene = homePage();

        applicationStage.setScene(currentScene);
        applicationStage.show();

    }

    public static BorderPane navbar(User currentUser){
        final double fontSizeLogo = 30;
        final double fontSizeBtn = 20;

        HBox leftNavContainer = new HBox();
        HBox rightNavContainer = new HBox();
        BorderPane container = new BorderPane();

        rightNavContainer.setAlignment(Pos.CENTER_RIGHT);
        leftNavContainer.setAlignment(Pos.CENTER_LEFT);
        leftNavContainer.setSpacing(8);

        // nav
        Label logo= new Label("Kfupm Event");
        logo.setFont(Font.font("Arial", fontSizeLogo));

        Button bt1 = new Button("Events");
        bt1.setFont(Font.font("Arial", fontSizeBtn));

        Button bt2 = new Button("My Tickets");
        bt2.setFont(Font.font("Arial", fontSizeBtn));

        leftNavContainer.getChildren().add(logo);
        if(currentUser.isAdmin()){
            Button bt3 = new Button("Admin Panel");
            bt3.setFont(Font.font("Arial", fontSizeBtn));
            leftNavContainer.getChildren().add(bt3);
        }
        leftNavContainer.getChildren().add(bt1);
        leftNavContainer.getChildren().add(bt2);

        // logout
        Button logoutBtn = new Button("Logout");
        logoutBtn.setFont(Font.font("Arial", fontSizeBtn));
        rightNavContainer.getChildren().add(logoutBtn);

        container.setLeft(leftNavContainer);
        container.setRight(rightNavContainer);

        return container;
    }

    public static User loginPage(Stage applicationStage){

        return new User("Yousef", "123", true);
    }

    public static Scene homePage(){
        BorderPane root = new BorderPane();

        //root
        root.setPadding(new Insets(10, 8, 10, 8));
        root.setTop(navbar(currentUser));

        return new Scene(root, winWidth, winHeight);
    }
}
