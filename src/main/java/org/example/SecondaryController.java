package org.example;

// ORGANIZATION TAB
import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class SecondaryController {

    public Label monthLbl;
    public static int chosenMonth = 0;
    public GridPane calendarTable;


    public void initialize() {
        setCalendarHeader();
        chosenMonth = LocalDate.now().getMonth().getValue();
        setCalendar(chosenMonth);
    }

    public void setCalendarHeader() {
        //setting up the headers of the calendar
        Label monLabel = new Label("Monday");
        monLabel.setStyle("-fx-padding: 0 0 0 10");
        Label tueLabel = new Label("Tuesday");
        tueLabel.setStyle("-fx-padding: 0 0 0 10");
        Label wedLabel = new Label("Wednesday");
        wedLabel.setStyle("-fx-padding: 0 0 0 10");
        Label thurLabel = new Label("Thursday");
        thurLabel.setStyle("-fx-padding: 0 0 0 10");
        Label friLabel = new Label("Friday");
        friLabel.setStyle("-fx-padding: 0 0 0 10");
        Label satLabel = new Label("Saturday");
        satLabel.setStyle("-fx-padding: 0 0 0 10");
        Label sunLabel = new Label("Sunday");
        sunLabel.setStyle("-fx-padding: 0 0 0 10");

        calendarTable.addRow(0, monLabel, tueLabel, wedLabel, thurLabel, friLabel, satLabel, sunLabel);
    }

    public void setCalendar(int month) {
        //Getting the date values
        LocalDate currentdate = LocalDate.of(2022, month, 1);
        int startDay = currentdate.getDayOfWeek().getValue();
        int daysInMonth = currentdate.lengthOfMonth();
        String monthName = currentdate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        monthLbl.setText(monthName);

        int week = 1;
        int day = 1;
        int totalCells = 6 * 7; //6 weeks 7 days
        int column = startDay - 1;
        for (int cDayCount = 0; cDayCount < totalCells; cDayCount++) {
            if (day <= daysInMonth) {
                if (cDayCount >= startDay) {
                    Label dayLbl =new Label(Integer.toString(day));
                    dayLbl.setStyle("-fx-background-color: Cyan");
                    VBox newDay = new VBox(dayLbl);
                    if (App.tasks.size()!=0){
                        Button btn = new Button();
                        btn.setText(App.tasks.get(0).toString());
                        btn.setOnAction(new EventHandler() {

                            @Override
                            public void handle (Event event){
                                System.out.println("Hi there! You clicked me");
                            }
                        });

                        newDay.getChildren().add(btn);
                    } else {
                        newDay.getChildren().add(new Label("No tasks today"));
                    }
                   //   newDay.getChildren().add(new Button("task2"));
                    calendarTable.setValignment(newDay, VPos.TOP);

                    calendarTable.add(newDay, column, week);
                    day++;
                    column++;
                    if (cDayCount % 7 == 0) {
                        week++;
                        column = 0;
                    }


                }
            }

        }


    }


    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToFourth() throws IOException {
        App.setRoot("fourth");
    }

    @FXML
    private void switchToFifth() throws IOException {
        App.setRoot("fifth");
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("third");
    }

    public void nextMonthBtn(ActionEvent actionEvent) {
        while (calendarTable.getChildren().size() > 7) {
            calendarTable.getChildren().remove(7);
        }
        chosenMonth++;
        setCalendar(chosenMonth);
    }
    public void previousMonthBtn (ActionEvent actionEvent) {
        while (calendarTable.getChildren().size() > 7) {
            calendarTable.getChildren().remove(7);
        }
        chosenMonth--;
        setCalendar(chosenMonth);
    }

    }
