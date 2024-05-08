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
            System.out.println("Users has been saved");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (final FileOutputStream fout = new FileOutputStream("Events.txt");
             final ObjectOutputStream out = new ObjectOutputStream(fout)) {
            for (int i = 0; i < Event.getEvents().size(); i++) {
                out.writeObject(Event.getEvents().get(i));
                out.flush();
            }
            System.out.println("Events has been saved");
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
        ScrollPane centerContainer = eventPage(applicationStage);

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
    public static ScrollPane eventPage(Stage applicationStage){
        // containers
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxHeight(WIN_HEIGHT * 0.8 );
        scrollPane.setMaxWidth(WIN_WIDTH * 0.85);
        scrollPane.setPadding(new Insets(10, 10, 10, 10));


        VBox vBox = new VBox(5);
        vBox.setMinWidth(WIN_WIDTH * 0.8);
        vBox.setMinHeight(WIN_HEIGHT * 0.8 - 20);
        scrollPane.setContent(vBox);

        for (int i = 0; i < Event.getEvents().size(); i++) {
            BorderPane box = eventBox(Event.getEvents().get(i), true, false);
            vBox.getChildren().add(box);
        }

        return scrollPane;

    }
    public static void myTicketPage(Stage applicationStage){
        // containers
        BorderPane generalContainer = new BorderPane();
        ScrollPane centerContainer = new ScrollPane();
        centerContainer.setMaxHeight(WIN_HEIGHT * 0.8 );
        centerContainer.setMaxWidth(WIN_WIDTH * 0.85);
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
                if (Event.getEvents().get(i).getTickets().get(j).getUser().equals(currentUser)){
                    BorderPane tickets = eventBox(Event.getEvents().get(i), false, false);
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
    public static BorderPane eventBox(Event event, boolean inEvent, boolean adminPanel){
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

            //delete btn
            Button deleteBtn = new Button("Delete");
            deleteBtn.setFont(Font.font("Comic Sans MS", 0.02 * WIN_WIDTH));
            buttonsContainer.getChildren().add(deleteBtn);

        }

        generalContainer.setLeft(leftContainer);
        generalContainer.setRight(rightContainer);
        return generalContainer;
    }

}
