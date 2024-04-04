package com.example.ics108project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
                            basicEvents(applicationStage);
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR,"Wrong credentials");
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
        applicationStage.setScene(scene);
        applicationStage.setTitle("Login");
        applicationStage.show();


        return new User("Yousef", "123", true);
    }

    public static User basicEvents(Stage applicationStage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        applicationStage.setWidth(winWidth);
        applicationStage.setHeight(winHeight);
        TableView tableView = new TableView<>();
        ObservableList<Event> contentList = FXCollections.observableArrayList(Event.getEvents());

        TableColumn<Event, String> titleColumn = new TableColumn("Title");
        TableColumn<Event, String> categoryColumn = new TableColumn("Category");
        TableColumn<Event, String> descriptionColumn = new TableColumn("Description");
        TableColumn<Event, String> dateColumn = new TableColumn("Date");
        TableColumn<Event, String> timeColumn = new TableColumn("Time");
        TableColumn<Event, String> locationColumn = new TableColumn("Location");
        TableColumn<Event, Integer> capacityColumn = new TableColumn("Capacity");
        TableColumn bookingColumn = new TableColumn("Booking");


        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacityNum"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateString"));
        bookingColumn.setCellValueFactory(new PropertyValueFactory("button"));
        tableView.getColumns().addAll(titleColumn, categoryColumn, descriptionColumn, dateColumn, timeColumn, locationColumn, capacityColumn, bookingColumn);
        tableView.setItems(contentList);
        tableView.setPrefWidth(winWidth);
        tableView.setPrefHeight(winHeight);
        root.getChildren().add(tableView);
        applicationStage.setScene(scene);
        applicationStage.show();
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
