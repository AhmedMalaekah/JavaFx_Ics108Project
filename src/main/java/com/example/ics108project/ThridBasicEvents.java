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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ThridBasicEvents extends Application {
    public static final double WIN_WIDTH = 800;
    public static final double WIN_HEIGHT = 700;
    final double fontSizeLogo = WIN_WIDTH * 0.04;
    final double fontSizeBtn = WIN_WIDTH * 0.025;
    @Override
    public void start(Stage stage) throws Exception {

        stage.setHeight(WIN_HEIGHT);
        stage.setWidth(WIN_WIDTH);
        BorderPane borderPane = new BorderPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Scene scene = new Scene(borderPane);
        Group group = groupClone();
        scrollPane.setContent(vBox);
        borderPane.setCenter(scrollPane);
        vBox.getChildren().addAll(group);
        vBox.setMinWidth(600);
        vBox.setMinHeight(520);
        stage.setScene(scene);
        stage.show();
    }
    public static Group groupClone(){
        Group clonedGroup = new Group();
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(175);
        rectangle.setWidth(600);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);
        Label eventTitle = new Label("Event Title");
        eventTitle.setLayoutX(13);
        eventTitle.setLayoutY(2);
        eventTitle.setFont(Font.font("Arial",27));
        Label byUser = new Label("by User");
        byUser.setLayoutX(156);
        byUser.setLayoutY(12);
        byUser.setFont(Font.font("Arial",14));
        Label category = new Label("category");
        category.setLayoutX(17);
        category.setLayoutY(39);
        category.setFont(Font.font("Arial",15));
        Label description = new Label("description");
        description.setLayoutX(17);
        description.setLayoutY(66);
        description.setFont(Font.font("Arial",15));
        Label dateAndTime = new Label("date:Time");
        dateAndTime.setLayoutX(498);
        dateAndTime.setLayoutY(12);
        dateAndTime.setFont(Font.font("Arial",15));
        Label location = new Label("location");
        location.setLayoutX(509);
        location.setLayoutY(39);
        location.setFont(Font.font("Arial",15));
        Button bookButton = new Button("book");
        bookButton.setLayoutX(530);
        bookButton.setLayoutY(130);
        bookButton.setFont(Font.font("Arial",15));
        Label seatsLeft = new Label("#seatsLeft");
        seatsLeft.setLayoutX(451);
        seatsLeft.setLayoutY(134);
        seatsLeft.setFont(Font.font("Arial",12));
        clonedGroup.getChildren().addAll(rectangle,eventTitle,byUser,category,description,dateAndTime,location,bookButton,seatsLeft);
        return clonedGroup;

    }

}
