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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BasicEvents extends Application {
    @Override
    public void start(Stage stage) throws Exception {
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
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
