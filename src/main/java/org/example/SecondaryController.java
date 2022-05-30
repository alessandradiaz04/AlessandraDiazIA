//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.awt.*;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SecondaryController {
    public Label monthLbl;
    public static int chosenMonth = 0;
    public GridPane calendarTable;
    private MenuItemBase btn;

    public SecondaryController() {
    }

    public void initialize() {
        this.setCalendarHeader();
        chosenMonth = LocalDate.now().getMonth().getValue();
        this.setCalendar(chosenMonth);
    }

    public void setCalendarHeader() {
        Label monLabel = new Label("Monday");
        monLabel.setStyle( "-fx-padding: 0 0 0 10");
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
        this.calendarTable.addRow(0, new Node[]{monLabel, tueLabel, wedLabel, thurLabel, friLabel, satLabel, sunLabel});
    }

    public void setCalendar(int month) {
        LocalDate currentdate = LocalDate.of(2022, month, 1);
        int startDay = currentdate.getDayOfWeek().getValue();
        int daysInMonth = currentdate.lengthOfMonth();
        String monthName = currentdate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.monthLbl.setText(monthName);



        int week = 1;
        int day = 1;
        int totalCells = 42;
        int column = startDay - 1;

        for(int cDayCount = 0; cDayCount < totalCells; ++cDayCount) {
            if (day <= daysInMonth && cDayCount >= startDay) {
                VBox newDay = new VBox(new Node[]{new Label(Integer.toString(day))});
                if (App.tasks.size()!=0){

                newDay.getChildren().add(new Button(App.tasks.get(0).toString()));
                } else{
                    newDay.getChildren().add(new Label("No tasks today"));

                }
                calendarTable.setValignment (newDay, VPos.TOP);

                this.calendarTable.add(newDay, column, week);
                ++day;
                ++column;
                if (cDayCount % 7 == 0) {
                    ++week;
                    column = 0;
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
        while(this.calendarTable.getChildren().size() > 7) {
            this.calendarTable.getChildren().remove(7);
        }

        ++chosenMonth;
        this.setCalendar(chosenMonth);
    }

    public void previousMonthBtn(ActionEvent actionEvent) {
        while(this.calendarTable.getChildren().size() > 7) {
            this.calendarTable.getChildren().remove(7);
        }

        --chosenMonth;
        this.setCalendar(chosenMonth);
    }
    @FXML
    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        System.out.println("Link clicked!");
        Desktop.getDesktop().browse(new URI("https://pomofocus.io/"));
    }
}
