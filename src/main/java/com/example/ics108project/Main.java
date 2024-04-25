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
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Authentication.User;

import java.text.ParseException;

public class Main extends Application {

    public static final double WIN_WIDTH = 800;
    public static final double WIN_HEIGHT = 700;
    public static final double HEADING_SIZE = 0.05 * WIN_WIDTH;
    public static final Font HEADING_FONT = Font.font("Comic Sans MS", HEADING_SIZE);
    public static final Insets PADDING  = new Insets(10, 8, 10, 8);
    public static User currentUser;
    @Override
    public void start(Stage applicationStage) throws ParseException {
        applicationStage.setTitle("Kfupm Event");
        Event.loadEvents();
        User.loadUsers();
        loginPage(applicationStage);

    }

    //Pages
    public static void loginPage(Stage applicationStage){
        final Font INPUT_FONT = Font.font("Arial", 0.03*WIN_WIDTH);
        final Insets FIELD_PADDING = new Insets(0, 0, 0, 10);
        final Font BTN_FONT = Font.font("Arial", WIN_WIDTH * 0.025);
        //containers and general settings
        BorderPane generalContainer = new BorderPane();
        VBox centerContainer = new VBox();

        centerContainer.setAlignment(Pos.TOP_CENTER);
        centerContainer.setMaxWidth(0.8*WIN_WIDTH);
        centerContainer.setSpacing(WIN_HEIGHT*0.05);
        centerContainer.setBorder(Border.stroke(Paint.valueOf("Gray")));
        centerContainer.setPadding(new Insets(15, 5, 10, 5));
        centerContainer.setMaxHeight(0.7*WIN_HEIGHT);
        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));



        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);

        //heading label
        Label registerHeading = new Label("Login");
        registerHeading.setFont(HEADING_FONT);
        centerContainer.getChildren().add(registerHeading);

        // USERNAME
        //container
        HBox usernameContainer = new HBox();
        usernameContainer.setAlignment(Pos.CENTER_LEFT);
        usernameContainer.setPadding(FIELD_PADDING);
        usernameContainer.setSpacing(WIN_WIDTH*0.03);

        //username label
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(INPUT_FONT);
        usernameContainer.getChildren().add(usernameLabel);

        //username field
        TextField usernameField = new TextField();
        usernameField.setMinWidth(WIN_WIDTH*0.3);
        usernameField.setMinHeight(WIN_HEIGHT*0.05);
        usernameContainer.getChildren().add(usernameField);

        centerContainer.getChildren().add(usernameContainer);

        // PASSWORD
        //container
        HBox passwordContainer = new HBox();
        passwordContainer.setAlignment(Pos.CENTER_LEFT);
        passwordContainer.setPadding(FIELD_PADDING);
        passwordContainer.setSpacing(WIN_WIDTH*0.03);

        //password label
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(INPUT_FONT);
        passwordContainer.getChildren().add(passwordLabel);

        //password field
        PasswordField passwordField = new PasswordField();
        passwordField.setMinWidth(WIN_WIDTH*0.3);
        passwordField.setMinHeight(WIN_HEIGHT*0.05);
        passwordContainer.getChildren().add(passwordField);

        centerContainer.getChildren().add(passwordContainer);

        //ADMIN
        //container
//        HBox adminContainer = new HBox();
//        adminContainer.setAlignment(Pos.CENTER_LEFT);
//        adminContainer.setPadding(FIELD_PADDING);
//        adminContainer.setSpacing(WIN_WIDTH*0.03);
//
//        //admin label
//        Label adminLabel = new Label("Admin:");
//        adminLabel.setFont(INPUT_FONT);
//        adminContainer.getChildren().add(adminLabel);
//
//        //admin field
//        CheckBox adminField = new CheckBox();
//        adminContainer.getChildren().add(adminField);


//        centerContainer.getChildren().add(adminContainer);

        //Buttons
        //container
        HBox buttonsContainer = new HBox();
        buttonsContainer.setAlignment(Pos.CENTER_LEFT);
        buttonsContainer.setPadding(FIELD_PADDING);
        buttonsContainer.setSpacing(WIN_WIDTH*0.01);

        //Save button
        Button loginButton = new Button("Login");
        loginButton.setFont(BTN_FONT);
        loginButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean userFound = false;

                int index = User.getUsernames().indexOf(usernameField.getText());
                if (index != -1){
                    userFound = true;

                }
                else {index =0;}
                try {
                    User user = User.getUsers().get(index);
                    currentUser = user;
                    {
                        if (user.getUsername().equals(usernameField.getText())&& user.checkPassword(passwordField.getText())&& userFound && !user.isAdmin()){
                            homePage(applicationStage);
                        } else if (user.getUsername().equals(usernameField.getText())&& user.checkPassword(passwordField.getText())&& user.isAdmin()&& userFound) {

                            homePage(applicationStage);

                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Wrong credentials");
                            alert.setContentText("You should enter correct credentials");
                            alert.showAndWait();
                        }
                    }
                }
                catch (Exception e){
                    System.out.println(userFound);
                    System.out.println("There are no users");
                }


            }
        });

        buttonsContainer.getChildren().add(loginButton);

        //login button
        Button registerButton = new Button("Register");
        registerButton.setFont(BTN_FONT);
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                registerPage(applicationStage);
            }
        });
        buttonsContainer.getChildren().add(registerButton);

        centerContainer.getChildren().add(buttonsContainer);
        // scene and stage
        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);
        applicationStage.setScene(scene);
        applicationStage.show();
    }

    public static void registerPage(Stage applicationStage){
        final Font INPUT_FONT = Font.font("Arial", 0.03*WIN_WIDTH);
        final Insets FIELD_PADDING = new Insets(0, 0, 0, 10);
        final Font BTN_FONT = Font.font("Arial", WIN_WIDTH * 0.025);
        //containers and general settings
        BorderPane generalContainer = new BorderPane();
        VBox centerContainer = new VBox();

        centerContainer.setAlignment(Pos.TOP_CENTER);
        centerContainer.setMaxWidth(0.8*WIN_WIDTH);
        centerContainer.setSpacing(WIN_HEIGHT*0.05);
        centerContainer.setBorder(Border.stroke(Paint.valueOf("Gray")));
        centerContainer.setPadding(new Insets(15, 5, 10, 5));
        centerContainer.setMaxHeight(0.7*WIN_HEIGHT);
        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));



        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);

        //heading label
        Label registerHeading = new Label("Register");
        registerHeading.setFont(HEADING_FONT);
        centerContainer.getChildren().add(registerHeading);

        // USERNAME
        //container
        HBox usernameContainer = new HBox();
        usernameContainer.setAlignment(Pos.CENTER_LEFT);
        usernameContainer.setPadding(FIELD_PADDING);
        usernameContainer.setSpacing(WIN_WIDTH*0.03);

        //username label
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(INPUT_FONT);
        usernameContainer.getChildren().add(usernameLabel);

        //username field
        TextField usernameField = new TextField();
        usernameField.setMinWidth(WIN_WIDTH*0.3);
        usernameField.setMinHeight(WIN_HEIGHT*0.05);
        usernameContainer.getChildren().add(usernameField);

        centerContainer.getChildren().add(usernameContainer);

        // PASSWORD
        //container
        HBox passwordContainer = new HBox();
        passwordContainer.setAlignment(Pos.CENTER_LEFT);
        passwordContainer.setPadding(FIELD_PADDING);
        passwordContainer.setSpacing(WIN_WIDTH*0.03);

        //password label
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(INPUT_FONT);
        passwordContainer.getChildren().add(passwordLabel);

        //password field
        PasswordField passwordField = new PasswordField();
        passwordField.setMinWidth(WIN_WIDTH*0.3);
        passwordField.setMinHeight(WIN_HEIGHT*0.05);
        passwordContainer.getChildren().add(passwordField);

        centerContainer.getChildren().add(passwordContainer);

        //ADMIN
        //container
        HBox adminContainer = new HBox();
        adminContainer.setAlignment(Pos.CENTER_LEFT);
        adminContainer.setPadding(FIELD_PADDING);
        adminContainer.setSpacing(WIN_WIDTH*0.03);

        //admin label
        Label adminLabel = new Label("Admin:");
        adminLabel.setFont(INPUT_FONT);
        adminContainer.getChildren().add(adminLabel);

        //admin field
        CheckBox adminField = new CheckBox();
        adminContainer.getChildren().add(adminField);


        centerContainer.getChildren().add(adminContainer);

        //Buttons
        //container
        HBox buttonsContainer = new HBox();
        buttonsContainer.setAlignment(Pos.CENTER_LEFT);
        buttonsContainer.setPadding(FIELD_PADDING);
        buttonsContainer.setSpacing(WIN_WIDTH*0.01);

        //Save button
        Button saveBtn = new Button("Save");
        saveBtn.setFont(BTN_FONT);
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String username = usernameField.getText();
                String password = passwordField.getText();
                boolean admin = adminField.isSelected();
                if(User.validUsername(username)&& !username.isEmpty() && !password.isEmpty()){
                    User.createUser(username, password, admin);
                    loginPage(applicationStage);
                }
                else{
                    if (username.isEmpty() || password.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "You should fill all fields");
                        alert.showAndWait();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Username already used");
                        alert.showAndWait();
                    }
                }
            }
        });

        buttonsContainer.getChildren().add(saveBtn);

        //login button
        Button loginBtn = new Button("Go Back To Login");
        loginBtn.setFont(BTN_FONT);
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginPage(applicationStage);
            }
        });
        buttonsContainer.getChildren().add(loginBtn);

        centerContainer.getChildren().add(buttonsContainer);
        // scene and stage
        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);
        applicationStage.setScene(scene);
        applicationStage.show();
    }
    public static void homePage(Stage applicationStage){
        BorderPane generalContainer = new BorderPane();

        //root
        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(basicEvent(applicationStage));

        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);

        applicationStage.setScene(scene);
        applicationStage.show();
    }
    public static void eventsPage(Stage applicationStage) {
        Group root = new Group();
        Scene scene = new Scene(root);
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
        tableView.setPrefWidth(WIN_WIDTH);
        tableView.setPrefHeight(WIN_HEIGHT);
        root.getChildren().add(tableView);
        applicationStage.setScene(scene);
        applicationStage.show();
    }

    //Components
    public static BorderPane navbar(Stage applicationStage){
        final double fontSizeLogo = WIN_WIDTH * 0.04;
        final double fontSizeBtn = WIN_WIDTH * 0.025;

        HBox leftNavContainer = new HBox();
        HBox rightNavContainer = new HBox();
        BorderPane container = new BorderPane();
        container.setPadding(new Insets(0, 0, 5, 0));

        rightNavContainer.setAlignment(Pos.CENTER_RIGHT);
        leftNavContainer.setAlignment(Pos.CENTER_LEFT);
        leftNavContainer.setSpacing(8);

        // nav

        //logo
        Label logo= new Label("Kfupm Event");
        logo.setFont(Font.font("Georgia", fontSizeLogo));
        leftNavContainer.getChildren().add(logo);

        if(currentUser != null) {
            // admin panel
            if (currentUser.isAdmin()) {
                Button bt3 = new Button("Admin Panel");
                bt3.setFont(Font.font("Arial", fontSizeBtn));
                bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        registerPage(applicationStage);
                    }
                });
                leftNavContainer.getChildren().add(bt3);
            }

            // events
            Button bt1 = new Button("Events");
            bt1.setFont(Font.font("Arial", fontSizeBtn));
            bt1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    homePage(applicationStage);
                }
            });
            leftNavContainer.getChildren().add(bt1);

            // my tickets
            Button bt2 = new Button("My Tickets");
            bt2.setFont(Font.font("Arial", fontSizeBtn));
            bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    eventsPage(applicationStage);
                }
            });
            leftNavContainer.getChildren().add(bt2);


            // logout
            Button logoutBtn = new Button("Logout");
            logoutBtn.setFont(Font.font("Arial", fontSizeBtn));
            logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    currentUser = null;
                    loginPage(applicationStage);
                }
            });
            rightNavContainer.getChildren().add(logoutBtn);
        }
        container.setLeft(leftNavContainer);
        container.setRight(rightNavContainer);

        return container;
    }
    public static ScrollPane basicEvent(Stage applicationStage){
        // containers
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();

        vBox.setMinWidth(600);
        vBox.setMinHeight(520);

        scrollPane.setContent(vBox);

        for (int i = 0; i < Event.getEvents().size(); i++) {
            Group group = eventBox(Event.getEvents().get(i));
            vBox.getChildren().add(group);
        }

        return scrollPane;

    }
    public static Group eventBox(Event event){
        Group clonedGroup = new Group();
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(175);
        rectangle.setWidth(600);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);

        Label eventTitle = new Label("Event Title");
        eventTitle.setText(event.getTitle());
        eventTitle.setLayoutX(13);
        eventTitle.setLayoutY(2);
        eventTitle.setFont(Font.font("Arial",27));

        Label byUser = new Label("by User");
        byUser.setLayoutX(156);
        byUser.setLayoutY(12);
        byUser.setFont(Font.font("Arial",14));

        Label category = new Label("category");
        category.setText(event.getCategory());
        category.setLayoutX(17);
        category.setLayoutY(39);
        category.setFont(Font.font("Arial",15));

        Label description = new Label("description");
        description.setText(event.getDescription());
        description.setLayoutX(17);
        description.setLayoutY(66);
        description.setFont(Font.font("Arial",15));

        Label dateAndTime = new Label("date:Time");
        dateAndTime.setText(String.format("%s : %s",event.getDateString(),event.getTime()));
        dateAndTime.setLayoutX(450);
        dateAndTime.setLayoutY(12);
        dateAndTime.setFont(Font.font("Arial",15));

        Label location = new Label("location");
        location.setText(event.getLocation());
        location.setLayoutX(450);
        location.setLayoutY(39);
        location.setFont(Font.font("Arial",15));

        Button bookButton = new Button("book");
        bookButton.setLayoutX(530);
        bookButton.setLayoutY(130);
        bookButton.setFont(Font.font("Arial",15));

        Label seatsLeft = new Label("#seatsLeft");
        seatsLeft.setText(String.valueOf(event.getCapacityNum()));
        seatsLeft.setLayoutX(500);
        seatsLeft.setLayoutY(134);
        seatsLeft.setFont(Font.font("Arial",15));

        clonedGroup.getChildren().addAll(rectangle,eventTitle,byUser,category,description,dateAndTime,location,bookButton,seatsLeft);
        return clonedGroup;

    }

//        registerButton.setOnAction(new EventHandler<ActionEvent>() {
//        @Override
//        public void handle(ActionEvent actionEvent) {
//            registerPage(applicationStage);
//        }
//    });
}
