package com.example;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TutorMenuController {


    private List<String> selectedVariableNames; 

    @FXML
    private Button SettingsButtonTutor;

    @FXML
    private Button addButton;

    @FXML
    private Button chatButtonTutor;

    @FXML
    private Rectangle fri10;

    @FXML
    private Rectangle fri11;

    @FXML
    private Rectangle fri12;

    @FXML
    private Rectangle fri13;

    @FXML
    private Rectangle fri14;

    @FXML
    private Rectangle fri15;

    @FXML
    private Rectangle fri16;

    @FXML
    private Rectangle fri9;

    @FXML
    private Rectangle mon10;

    @FXML
    private Rectangle mon11;

    @FXML
    private Rectangle mon12;

    @FXML
    private Rectangle mon13;

    @FXML
    private Rectangle mon14;

    @FXML
    private Rectangle mon15;

    @FXML
    private Rectangle mon16;

    @FXML
    private Rectangle mon9;

    @FXML
    private Circle profileTutor;

    @FXML
    private Rectangle sat10;

    @FXML
    private Rectangle sat11;

    @FXML
    private Rectangle sat12;

    @FXML
    private Rectangle sat13;

    @FXML
    private Rectangle sat14;

    @FXML
    private Rectangle sat15;

    @FXML
    private Rectangle sat16;

    @FXML
    private Rectangle sat9;

    @FXML
    private Rectangle sun10;

    @FXML
    private Rectangle sun11;

    @FXML
    private Rectangle sun12;

    @FXML
    private Rectangle sun13;

    @FXML
    private Rectangle sun14;

    @FXML
    private Rectangle sun15;

    @FXML
    private Rectangle sun16;

    @FXML
    private Rectangle sun9;

    @FXML
    private Rectangle thu10;

    @FXML
    private Rectangle thu11;

    @FXML
    private Rectangle thu12;

    @FXML
    private Rectangle thu13;

    @FXML
    private Rectangle thu14;

    @FXML
    private Rectangle thu15;

    @FXML
    private Rectangle thu16;

    @FXML
    private Rectangle thu9;

    @FXML
    private Rectangle tue10;

    @FXML
    private Rectangle tue11;

    @FXML
    private Rectangle tue12;

    @FXML
    private Rectangle tue13;

    @FXML
    private Rectangle tue14;

    @FXML
    private Rectangle tue15;

    @FXML
    private Rectangle tue16;

    @FXML
    private Rectangle tue9;

    @FXML
    private Text userMail;

    @FXML
    private Text userName;

    @FXML
    private Rectangle wed10;

    @FXML
    private Rectangle wed11;

    @FXML
    private Rectangle wed12;

    @FXML
    private Rectangle wed13;

    @FXML
    private Rectangle wed14;

    @FXML
    private Rectangle wed15;

    @FXML
    private Rectangle wed16;

    @FXML
    private Rectangle wed9;

    public TutorMenuController()
    {

    }


    List<Rectangle> rectangles = null;

    @FXML
    public void initialize()
    {

        User currentUser = User.getCurrentUser();
        userMail.setText(currentUser.getEmail());
        userName.setText(currentUser.getUsername());

        chatButtonTutor.setOnAction(event -> {
            try {
                handleChatButtonTutor(event);
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        });

        SettingsButtonTutor.setOnAction(event -> {
            try {
                handleSettingsButtonTutor(event);
            } catch (IOException e) {
                e.printStackTrace(); 
            }
        });



        List<Rectangle> rectangles = List.of(
            fri10, fri11, fri12, fri13, fri14, fri15, fri16, fri9,
            mon10, mon11, mon12, mon13, mon14, mon15, mon16, mon9,
            sat10, sat11, sat12, sat13, sat14, sat15, sat16, sat9,
            sun10, sun11, sun12, sun13, sun14, sun15, sun16, sun9,
            thu10, thu11, thu12, thu13, thu14, thu15, thu16, thu9,
            tue10, tue11, tue12, tue13, tue14, tue15, tue16, tue9,
            wed10, wed11, wed12, wed13, wed14, wed15, wed16, wed9
        );

        // Attach a click event to each rectangle
        for (Rectangle rectangle : rectangles) {
            rectangle.setOnMouseClicked(event -> colorRectangle(rectangle));
        }
        ArrayList<Timeslot> reservedTimeslots = new ArrayList<>();
        try {
            reservedTimeslots = Timeslot.searchReservedTimeslots(User.getCurrentUser().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Timeslot timeslot: reservedTimeslots){
            for(Rectangle rectangle: rectangles){
                if(timeslot.getTimeSlot().equalsIgnoreCase(getVariableName(rectangle))){
                    rectangle.setFill(Color.GRAY);
                }
            }
        }

    }

    public void handleChatButtonTutor(ActionEvent event) throws IOException
    {
        App.setRoot("listOfChats");
    }

    public void handleSettingsButtonTutor(ActionEvent event) throws IOException
    {
        App.setRoot("settings");
    }

    public void handleAddButton(ActionEvent event) throws IOException
    {
        App.setRoot("AddACoursePage");
    }

    public Timeslot colorRectangle(Rectangle rectangle)
    {
        Timeslot newTimeslot = null;
        if(rectangle.getFill().equals(Color.WHITE)){
            rectangle.setFill(Color.GRAY); 
            Timeslot.createTimeslot(User.getCurrentUser().getId(), -1, getVariableName(rectangle));
        }
        else 
        {
            rectangle.setFill(Color.WHITE);
        }
        return newTimeslot;
    }

    private String getVariableName(Object object) {
        try {
            for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true); 
                if (field.get(this) == object) {
                    return field.getName();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null; 
    }


}
