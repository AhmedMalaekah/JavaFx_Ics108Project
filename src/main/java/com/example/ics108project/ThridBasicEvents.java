package com.example.ics108project;
import Authentication.User;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ThridBasicEvents extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(700);
        AnchorPane anchorPane = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        Scene scene = new Scene(anchorPane);
        Group group = new Group();
        anchorPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        Label programTitle = new Label("KFUPM Events");
        programTitle.setLayoutX(14);
        programTitle.setLayoutY(14);
        programTitle.setScaleX(3);
        programTitle.setScaleY(3);
        Button eventsButton = new Button("Events");
        eventsButton.setLayoutX(258);
        eventsButton.setLayoutY(27);
        eventsButton.setScaleX(1.2);
        eventsButton.setScaleY(1.2);
        Button myEventsButton = new Button("myEvents");
        myEventsButton.setLayoutX(330.0);
        myEventsButton.setLayoutY(27);
        myEventsButton.setScaleX(1.2);
        myEventsButton.setScaleY(1.2);
        anchorPane.getChildren().addAll(programTitle,eventsButton,myEventsButton);
        vBox.getChildren().add(group);
        Rectangle rectangle = new Rectangle();
        Label eventTitle = new Label("Event Title");
        Label byUser = new Label("by User");
        Label category = new Label("category");
        Label description = new Label("description");
        Label dateAndTime = new Label("date:Time");
        Label location = new Label("location");
        Button bookButton = new Button("book");
        Label seatsLeft = new Label("#seatsLeft");
        group.getChildren().addAll(rectangle,eventTitle,byUser,category,description,dateAndTime,location,bookButton,seatsLeft);
        stage.setScene(scene);
        stage.show();



    }
}
