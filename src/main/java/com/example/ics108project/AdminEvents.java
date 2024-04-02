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

import java.util.Date;

public class AdminEvents extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        TableView  eventsTable = new TableView<>();
        ObservableList<Event> contentList = FXCollections.observableArrayList(Event.getEvents());

        TableColumn<Event,String> titleColumn = new  TableColumn("title");
        TableColumn<Event,String> categoryColumn = new  TableColumn("category");
        TableColumn<Event,String> descriptionColumn = new  TableColumn("description");
        TableColumn<Event,String> dateColumn = new  TableColumn("date");
        TableColumn<Event,String> timeColumn = new  TableColumn("time");
        TableColumn<Event,String> locationColumn = new  TableColumn("location");
        TableColumn<Event,Integer> capacityColumn = new  TableColumn("capacity");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title") );
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacityNum"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateString"));

//        titleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        capacityColumn.setCellFactory(TextFieldTableCell.forTableColumn( ));
//        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        titleColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setTitle(t.getNewValue());
//        });
//
//        categoryColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setCategory(t.getNewValue());
//        });
//
//        descriptionColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setDescription(t.getNewValue());
//        });
//
//        timeColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setTime(t.getNewValue());
//        });
//
//        locationColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setLocation(t.getNewValue());
//        });
//
//        dateColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, String> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setDate(t.getNewValue());
//        });
//
//        capacityColumn.setOnEditCommit((TableColumn.CellEditEvent<Event, Integer> t) -> {
//            ((Event) t.getTableView()
//                    .getItems()
//                    .get(t.getTablePosition().getRow()))
//                    .setCapacity(t.getNewValue());
//        });



        pane.getChildren().add(eventsTable);
        eventsTable.getColumns().addAll(titleColumn,categoryColumn,descriptionColumn,dateColumn,timeColumn,locationColumn,capacityColumn);
        eventsTable.setItems(contentList);

        stage.setTitle("Kfupm Events Manger");
        stage.setScene(scene);
        stage.show();



    }
    public static void main(String[] args) {
        launch(args);
    }
}
