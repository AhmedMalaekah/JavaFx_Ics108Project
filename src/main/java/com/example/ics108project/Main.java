package com.example.ics108project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;

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
        User.loadUsers();
        Event.loadEvents();
        loginPage(applicationStage);

    }
    @Override
    public void stop() {
        try (final FileOutputStream fout = new FileOutputStream("Users.txt");
             final ObjectOutputStream out = new ObjectOutputStream(fout)) {
            for (int i = 0; i < User.getUsers().size(); i++) {
                out.writeObject(User.getUsers().get(i));
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (final FileOutputStream fout = new FileOutputStream("Events.txt");
             final ObjectOutputStream out = new ObjectOutputStream(fout)) {
            for (int i = 0; i < Event.getEvents().size(); i++) {
                out.writeObject(Event.getEvents().get(i));
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


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
                try {
                    if(userFound) {
                        User user = User.getUsers().get(index);
                        if (user.checkPassword(passwordField.getText())) {
                            currentUser = user;
                            homePage(applicationStage);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Wrong credentials");
                            alert.setContentText("You should enter correct credentials");
                            alert.showAndWait();
                        }
                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Wrong credentials");
                        alert.setContentText("You should enter correct credentials");
                        alert.showAndWait();
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
        // Settings for events page
        ScrollPane centerContainer = eventsBLock(applicationStage);
        centerContainer.setMaxHeight(WIN_HEIGHT * 0.9);

        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));

        //root
        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);

        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);

        applicationStage.setScene(scene);
        applicationStage.show();
    }
    public static void adminPage(Stage applicationStage){
        // containers
        BorderPane generalContainer = new BorderPane();
        ScrollPane centerContainer = new ScrollPane();
        centerContainer.setMaxHeight(WIN_HEIGHT * 0.9);
        centerContainer.setMaxWidth(WIN_WIDTH * 0.80 + 20);
        centerContainer.setPadding(new Insets(10, 10, 10, 10));

        // center container alignment
        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));

        //root
        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);


        VBox eventBoxContainer = new VBox(5);
        eventBoxContainer.setMinWidth(WIN_WIDTH * 0.8);
        eventBoxContainer.setMinHeight(WIN_HEIGHT * 0.8 - 20);
        centerContainer.setContent(eventBoxContainer);


        for (int i = 0; i < Event.getEvents().size(); i++) {
            BorderPane box = eventBox(Event.getEvents().get(i), false, true, applicationStage);
            eventBoxContainer.getChildren().add(box);
        }

        // create event btn
        Button createBtn = new Button("Create Event");
        createBtn.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));
        createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createEventPage(applicationStage);
            }
        });
        eventBoxContainer.getChildren().add(createBtn);

        // scene and stage
        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);
        applicationStage.setScene(scene);
        applicationStage.show();
    }
    public static void editEventPage(Stage applicationStage, Event event){
        //containers and general settings
        BorderPane generalContainer = new BorderPane();
        VBox centerContainer = evnetInput(event, applicationStage);

        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);
        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));

        // scene and stage
        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);
        applicationStage.setScene(scene);
        applicationStage.show();
    }
    public static void createEventPage(Stage applicationStage){
        //containers and general settings
        BorderPane generalContainer = new BorderPane();
        VBox centerContainer = evnetInput(null, applicationStage);

        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);
        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));

        // scene and stage
        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);
        applicationStage.setScene(scene);
        applicationStage.show();
    }
    public static void myTicketPage(Stage applicationStage){
        // containers
        BorderPane generalContainer = new BorderPane();
        ScrollPane centerContainer = new ScrollPane();
        centerContainer.setMaxHeight(WIN_HEIGHT * 0.8 );
        centerContainer.setMaxWidth(WIN_WIDTH * 0.80 + 20);
        centerContainer.setPadding(new Insets(10, 10, 10, 10));

        // Settings for ticket page
        BorderPane.setAlignment(centerContainer, Pos.TOP_CENTER);
        BorderPane.setMargin(centerContainer, new Insets(WIN_HEIGHT*0.03, 0, 0, 0));



        //root
        generalContainer.setPadding(PADDING);
        generalContainer.setTop(navbar(applicationStage));
        generalContainer.setCenter(centerContainer);


        VBox vBox = new VBox(5);
        vBox.setMinWidth(WIN_WIDTH * 0.8);
        vBox.setMinHeight(WIN_HEIGHT * 0.8 - 20);
        centerContainer.setContent(vBox);


        for (int i = 0; i < Event.getEvents().size(); i++) {
            for (int j = 0; j < Event.getEvents().get(i).getTickets().size(); j++) {
                if (Event.getEvents().get(i).getTickets().get(j).getUsername().equals(currentUser.getUsername())){
                    BorderPane tickets = eventBox(Event.getEvents().get(i), false, false, applicationStage);
                    vBox.getChildren().add(tickets);

                }
            }
        }


        // scene and stage
        Scene scene = new Scene(generalContainer, WIN_WIDTH, WIN_HEIGHT);
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
                        adminPage(applicationStage);
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
                    myTicketPage(applicationStage);
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
    public static BorderPane eventBox(Event event, boolean inEvent, boolean adminPanel, Stage applicationStage){
        // general container
        BorderPane generalContainer = new BorderPane();
        generalContainer.setPadding(new Insets(10, 10, 10, 10));
        generalContainer.setMaxWidth(WIN_WIDTH * 0.8 );
        generalContainer.setMinHeight(WIN_HEIGHT * 0.25);
        generalContainer.setBackground(new Background(new BackgroundFill(Color.rgb(220, 220, 220), CornerRadii.EMPTY, Insets.EMPTY)));

        // leftContainer containers
        VBox leftContainer = new VBox();
        leftContainer.setAlignment(Pos.TOP_LEFT);

        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.BOTTOM_LEFT);
        titleContainer.setSpacing(10);


        leftContainer.getChildren().add(titleContainer);


        // Title and by User
        Label titleLabel = new Label(event.getTitle());
        titleLabel.setFont(Font.font("Comic Sans MS", 0.04*WIN_WIDTH));
        titleContainer.getChildren().add(titleLabel);

        Label byUserLabel = new Label("by " + event.getUser().getUsername());
        byUserLabel.setFont(Font.font("Comic Sans MS", 0.02*WIN_WIDTH));
        titleContainer.getChildren().add(byUserLabel);

        // category
        Label categoryLabel = new Label(event.getCategory());
        categoryLabel.setUnderline(true);
        categoryLabel.setFont(Font.font("Comic Sans MS", 0.02*WIN_WIDTH));
        leftContainer.getChildren().add(categoryLabel);

        // description
        Label descriptionLabel = new Label(event.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(0.6 * WIN_WIDTH);
        descriptionLabel.setFont(Font.font("Comic Sans MS", 0.02*WIN_WIDTH));
        leftContainer.getChildren().add(descriptionLabel);

        // header 2 containers
        BorderPane rightContainer = new BorderPane();
        VBox dateTimeLocationContainer = new VBox();

        rightContainer.setTop(dateTimeLocationContainer);

        // Date and time and location
        Label dateTimeLabel = new Label(event.getDateString() + " - " + event.getTime());
        dateTimeLocationContainer.getChildren().add(dateTimeLabel);

        Label locationLabel = new Label(event.getLocation());
        locationLabel.setFont(Font.font("Comic Sans MS", 0.02*WIN_WIDTH));
        dateTimeLocationContainer.getChildren().add(locationLabel);

        if(inEvent) {
            HBox seatsBtnContainer = new HBox();
            seatsBtnContainer.setAlignment(Pos.CENTER_RIGHT);
            seatsBtnContainer.setSpacing(10);
            rightContainer.setBottom(seatsBtnContainer);

            //Number of seats and book btn
            Label numOfSeatsLabel = new Label(event.getNumTicketsAvailable() + " / " + event.getCapacityNum());
            numOfSeatsLabel.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));
            seatsBtnContainer.getChildren().add(numOfSeatsLabel);

            Button bookBtn = new Button("Book");
            bookBtn.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));
            seatsBtnContainer.getChildren().add(bookBtn);

            bookBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(event.isUpcoming()){
                        if (event.getNumTicketsAvailable() > 0){
                            event.addTicket(currentUser);
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText("Event booked successfully!");
                            alert.showAndWait();
                            homePage(applicationStage);

                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Not enough seats!");
                            alert.showAndWait();
                        }

                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("you can't book past events");
                        alert.showAndWait();
                    }
                }
            });
        }
        if(adminPanel){
            HBox buttonsContainer = new HBox();
            buttonsContainer.setAlignment(Pos.CENTER_RIGHT);
            buttonsContainer.setSpacing(10);
            rightContainer.setBottom(buttonsContainer);

            // edit btn
            Button editBtn = new Button("Edit");
            editBtn.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));
            buttonsContainer.getChildren().add(editBtn);

            editBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    editEventPage(applicationStage, event);
                }
            });

            //delete btn
            Button deleteBtn = new Button("Delete");
            deleteBtn.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));
            deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    Event.delEvent(event);
                    adminPage(applicationStage);
                }

            });
            buttonsContainer.getChildren().add(deleteBtn);

        }

        generalContainer.setLeft(leftContainer);
        generalContainer.setRight(rightContainer);
        return generalContainer;
    }
    public static ScrollPane eventsBLock(Stage applicationStage){
        // containers
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxHeight(WIN_HEIGHT * 0.8 );
        scrollPane.setMaxWidth(WIN_WIDTH * 0.80 + 20);
        scrollPane.setPadding(new Insets(10, 10, 10, 10));


        VBox vBox = new VBox(5);
        vBox.setMinWidth(WIN_WIDTH * 0.8);
        vBox.setMinHeight(WIN_HEIGHT * 0.8 - 20);
        scrollPane.setContent(vBox);

        for (int i = 0; i < Event.getEvents().size(); i++) {
            BorderPane box = eventBox(Event.getEvents().get(i), true, false, applicationStage);
            vBox.getChildren().add(box);
        }

        return scrollPane;

    }
    public static VBox evnetInput(Event event, Stage applicationStage){
        final Font INPUT_FONT = Font.font("Arial", 0.03*WIN_WIDTH);
        final Insets FIELD_PADDING = new Insets(0, 0, 0, 10);
        final Font BTN_FONT = Font.font("Arial", WIN_WIDTH * 0.025);

        // general container settings
        VBox generalContainer = new VBox();

        generalContainer.setAlignment(Pos.TOP_CENTER);
        generalContainer.setMaxWidth(0.8*WIN_WIDTH);
        generalContainer.setSpacing(WIN_HEIGHT*0.05);
        generalContainer.setBorder(Border.stroke(Paint.valueOf("Gray")));
        generalContainer.setPadding(new Insets(15, 5, 10, 5));
        generalContainer.setMaxHeight(0.8*WIN_HEIGHT);

        // event heading
        Label eventHeading = new Label("Event");
        eventHeading.setFont(HEADING_FONT);
        generalContainer.getChildren().add(eventHeading);

        // TITLE
        //container
        HBox titleContainer = new HBox();
        titleContainer.setAlignment(Pos.CENTER_LEFT);
        titleContainer.setPadding(FIELD_PADDING);
        titleContainer.setSpacing(WIN_WIDTH*0.03);

        //title label
        Label titleLabel = new Label("Title:");
        titleLabel.setFont(INPUT_FONT);
        titleContainer.getChildren().add(titleLabel);

        //title field
        TextField titleField = new TextField();
        titleField.setMinWidth(WIN_WIDTH*0.3);
        titleField.setMinHeight(WIN_HEIGHT*0.05);
        titleContainer.getChildren().add(titleField);

        generalContainer.getChildren().add(titleContainer);

        // CATEGORY
        //container
        HBox categoryContainer = new HBox();
        categoryContainer.setAlignment(Pos.CENTER_LEFT);
        categoryContainer.setPadding(FIELD_PADDING);
        categoryContainer.setSpacing(WIN_WIDTH*0.03);

        //category label
        Label categoryLabel = new Label("Category:");
        categoryLabel.setFont(INPUT_FONT);
        categoryContainer.getChildren().add(categoryLabel);

        //category field
        TextField categoryField = new TextField();
        categoryField.setMinWidth(WIN_WIDTH*0.3);
        categoryField.setMinHeight(WIN_HEIGHT*0.05);
        categoryContainer.getChildren().add(categoryField);

        generalContainer.getChildren().add(categoryContainer);

        // DESCRIPTION
        //container
        VBox descriptionContainer = new VBox();
        descriptionContainer.setAlignment(Pos.CENTER_LEFT);
        descriptionContainer.setPadding(FIELD_PADDING);
        descriptionContainer.setSpacing(WIN_WIDTH*0.03);

        //description label
        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setFont(INPUT_FONT);
        descriptionContainer.getChildren().add(descriptionLabel);

        //description field
        TextArea descriptionField = new TextArea();
        descriptionContainer.getChildren().add(descriptionField);

        generalContainer.getChildren().add(descriptionContainer);

        // DATE and TIME
        // container
        HBox dateTimeContainer = new HBox();
        dateTimeContainer.setAlignment(Pos.CENTER_LEFT);
        dateTimeContainer.setPadding(FIELD_PADDING);
        dateTimeContainer.setSpacing(WIN_WIDTH*0.03);

        // date label
        Label dateLabel = new Label("Date:");
        dateLabel.setFont(INPUT_FONT);
        dateTimeContainer.getChildren().add(dateLabel);

        // date field
        DatePicker dateField = new DatePicker();
        dateField.setValue(LocalDate.now());
        dateTimeContainer.getChildren().add(dateField);

        //time label
        Label timeLabel = new Label("Time:");
        timeLabel.setFont(INPUT_FONT);
        dateTimeContainer.getChildren().add(timeLabel);

        //time field
        TextField timeField = new TextField();
        timeField.setMinWidth(WIN_WIDTH*0.3);
        timeField.setMinHeight(WIN_HEIGHT*0.05);
        dateTimeContainer.getChildren().add(timeField);

        generalContainer.getChildren().add(dateTimeContainer);

        //LOCATION
        //container
        HBox locationContainer = new HBox();
        locationContainer.setAlignment(Pos.CENTER_LEFT);
        locationContainer.setPadding(FIELD_PADDING);
        locationContainer.setSpacing(WIN_WIDTH*0.03);

        //location label
        Label locationLabel = new Label("Location:");
        locationLabel.setFont(INPUT_FONT);
        locationContainer.getChildren().add(locationLabel);

        //location field
        TextField locationField = new TextField();
        locationField.setMinWidth(WIN_WIDTH*0.3);
        locationField.setMinHeight(WIN_HEIGHT*0.05);
        locationContainer.getChildren().add(locationField);

        generalContainer.getChildren().add(locationContainer);

        //SAVE BTN
        // I have added this button to the container at the end of the function
        Button saveBtn = new Button("Save");
        saveBtn.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));

        // create event extra fields
        if(event == null){
            //CAPACITY
            //container
            HBox capacityContainer = new HBox();
            capacityContainer.setAlignment(Pos.CENTER_LEFT);
            capacityContainer.setPadding(FIELD_PADDING);
            capacityContainer.setSpacing(WIN_WIDTH*0.03);

            // capacity label
            Label capacityLabel = new Label("Capacity: ");
            capacityLabel.setFont(INPUT_FONT);
            capacityContainer.getChildren().add(capacityLabel);

            //capacity field
            TextField capacityField = new TextField();
            capacityField.setText("1");
            capacityField.setMinWidth(WIN_WIDTH*0.3);
            capacityField.setMinHeight(WIN_HEIGHT*0.05);
            capacityContainer.getChildren().add(capacityField);

            generalContainer.getChildren().add(capacityContainer);

            // save for create
            saveBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Event.createEvent(titleField.getText(), categoryField.getText(), descriptionField.getText(), dateField.getValue(), timeField.getText(), locationField.getText(), Integer.parseInt(capacityField.getText()), currentUser);
                        adminPage(applicationStage);
                    }
                    catch (Exception ex){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Wrong input!");
                        alert.setContentText("You should enter correct input format");
                        alert.showAndWait();
                    }
                }
            });

        }
        else{
            titleField.setText(event.getTitle());
            categoryField.setText(event.getCategory());
            descriptionField.setText(event.getDescription());
            dateField.setValue(event.getDate());
            timeField.setText(event.getTime());
            locationField.setText(event.getLocation());

            // save for edit
            saveBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        event.editEvent(titleField.getText(), categoryField.getText(), descriptionField.getText(), dateField.getValue(), timeField.getText(), locationField.getText(), currentUser);
                        adminPage(applicationStage);
                    }
                    catch (Exception ex){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Wrong input!");
                        alert.setContentText("You should enter correct input format");
                        alert.showAndWait();
                    }
                }
            });
        }

        generalContainer.getChildren().add(saveBtn);

        return generalContainer;
    }

}
