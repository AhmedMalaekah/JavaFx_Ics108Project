package com.example.ics108project;

import com.example.ics108project.User;
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
                else {index =0;}
                try {
                    User user = User.getUsers().get(index);
                    {
                        if (user.getUsername().equals(userField.getText())&& user.checkPassword(passField.getText())&& user.isAdmin()&& userFound){
                            System.out.println("login done successfully");
                            System.out.println("I am an admin");
                        }
                        else if (user.getUsername().equals(userField.getText())&& user.checkPassword(passField.getText())&& !user.isAdmin()&& userFound){
                            System.out.println("login done successfully");
                            System.out.println("I am an basic user");
                            basicEvents(stage);
                        }
                        else {
                            Alert alert = new Alert(AlertType.ERROR,"Wrong credentials");
                            alert.showAndWait();
                        }
                    }
                }
                catch (Exception e){
                    System.out.println("There are no users");
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

    public void basicEvents(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setWidth(700);
        stage.setHeight(500);
        TableView tableView = new TableView<>();
        ObservableList<Event> contentList = FXCollections.observableArrayList(Event.getEvents());

        TableColumn<Event,String> titleColumn = new  TableColumn("Title");
        TableColumn<Event,String> categoryColumn = new  TableColumn("Category");
        TableColumn<Event,String> descriptionColumn = new  TableColumn("Description");
        TableColumn<Event,String> dateColumn = new  TableColumn("Date");
        TableColumn<Event,String> timeColumn = new  TableColumn("Time");
        TableColumn<Event,String> locationColumn = new  TableColumn("Location");
        TableColumn<Event,Integer> capacityColumn = new  TableColumn("Capacity");
        TableColumn bookingColumn = new  TableColumn("Booking");


        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacityNum"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateString"));
        bookingColumn.setCellValueFactory(new PropertyValueFactory("button"));
        tableView.getColumns().addAll(titleColumn,categoryColumn,descriptionColumn,dateColumn,timeColumn,locationColumn,capacityColumn,bookingColumn);
        tableView.setItems(contentList);
        tableView.setPrefWidth(700);
        tableView.setPrefHeight(500);
        root.getChildren().add(tableView);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
