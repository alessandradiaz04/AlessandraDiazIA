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

public class OrganizationController {
    public Label monthLbl;
    public static int chosenMonth = 0;
    public GridPane calendarTable;
    private MenuItemBase btn;

    public OrganizationController() {
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

        //Getting the date values
        LocalDate currentdate = LocalDate.of(2022, month, 1); //current date read by program

        int startDay = currentdate.getDayOfWeek().getValue(); //day number 1-7
        int daysInMonth = currentdate.lengthOfMonth(); //num of days in month
        String monthName = currentdate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.monthLbl.setText(monthName); //Display the corresponding month

        int week = 1; //the first week
        int day = 1; //the first day of the week
        int totalCells = 42; //weeks of the year
        int column = startDay - 1; //set to first week

        for(int cDayCount = 0; cDayCount < totalCells; ++cDayCount) {
            //The Day Count is set to 0, and as long as it is less than the total cells, it will increase by 1

            if (day <= daysInMonth && cDayCount >= startDay) {
                VBox newDay = new VBox(new Node[]{new Label(Integer.toString(day))});
                if (App.tasks.size()!=0){
                    for (Task t : App.tasks) {
                        int taskDay  = 0;
                        int taskMonth = 0;
                        taskDay = t.getDueDate().getDayOfMonth(); //get data of the task's date
                        taskMonth = t.getDueDate().getMonthValue(); //get data of the task's month
                        if(taskDay == day && taskMonth == month){
                            newDay.getChildren().add(new Button(App.tasks.get(0).toString()));
                        }
                    }
                } else{
                    newDay.getChildren().add(new Label("No tasks today"));
                    //When NO TASKS are detected in the tasks array
                }
                calendarTable.setValignment (newDay, VPos.TOP);

                this.calendarTable.add(newDay, column, week);
                ++day;
                ++column;
                if (cDayCount % 7 == 0) {
                    ++week;
                    column = 0;
                } }
        } }
    @FXML
    private void switchToWelcomePage() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToTasks() throws IOException {
        App.setRoot("fourth");
    }
    @FXML
    private void switchToGanttChart() throws IOException {
        App.setRoot("fifth");
    }
    @FXML
    private void switchToMindfulness() throws IOException {
        App.setRoot("third");
    }
    public void nextMonthBtn(ActionEvent actionEvent) { //Method to change month to the upcoming ones, with a button
        while(this.calendarTable.getChildren().size() > 7) {
            this.calendarTable.getChildren().remove(7);
        }
        ++chosenMonth; //Increase the month, meaning, move to the following
        this.setCalendar(chosenMonth); //Set calendar dates to corresponding ones
    }
    public void previousMonthBtn(ActionEvent actionEvent) { //Method to change month to the past ones, with a button
        while(this.calendarTable.getChildren().size() > 7) {
            this.calendarTable.getChildren().remove(7);
        }
        --chosenMonth; //Decrease the month, meaning, move to the previous
        this.setCalendar(chosenMonth); //Set calendar dates to corresponding ones
    }
    @FXML
    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        System.out.println("Link clicked!");
        Desktop.getDesktop().browse(new URI("https://pomofocus.io/"));
    }
}
