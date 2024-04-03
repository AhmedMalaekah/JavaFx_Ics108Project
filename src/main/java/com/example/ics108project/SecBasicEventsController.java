package com.example.ics108project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;

public class SecBasicEventsController {

//    private void duplicateGroup(){ Group duplicatedGroup = new Group(); duplicatedGroup.getProperties().putAll(groupForEvent.getProperties()); duplicatedGroup.setStyle(groupForEvent.getStyle()); duplicatedGroup.setLayoutX(groupForEvent.getLayoutX()+50); duplicatedGroup.setLayoutY(groupForEvent.getLayoutY()+50); scrollPane.getChildren().add(duplicatedGroup); }

    @FXML
    private Button bookButton;

    @FXML
    private Label byusertext;

    @FXML
    private Label categoryText;

    @FXML
    private Label dateTimeText;

    @FXML
    private Label descriptonText;

    @FXML
    private Button eventsButton;

    @FXML
    private Label eventtitleText;

    @FXML
    private Group groupForEvent;

    @FXML
    private Label kfupmText;

    @FXML
    private Label locationText;

    @FXML
    private Button myEventsButton;

    @FXML
    private Rectangle rectangleBox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label seatsLeftText;

    @FXML
    void bookEvent(ActionEvent event) {

    }

    @FXML
    void goToEvents(ActionEvent event) {

    }

    @FXML
    void goToMyEvents(ActionEvent event) {

    }

}
