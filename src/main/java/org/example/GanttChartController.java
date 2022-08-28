package org.example;

// "Gantt chart" TAB

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.Locale;


public class GanttChartController {

    public GridPane ganttChartGridPane;
    private Object Task;


    public void initialize() {
        int weekNumber  = 1;
        int month = 1;
        ganttChartGridPane.getColumnConstraints().remove(0);
        ColumnConstraints taskColumn = new ColumnConstraints(100);
        ganttChartGridPane.getColumnConstraints().add(taskColumn);


        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/ColumnConstraints.html
        ganttChartGridPane.setStyle("-fx-grid-lines-visible:true;");
        for(int i = 0; i < (365/7);i++){
            ColumnConstraints week = new ColumnConstraints(50); //in pixels
            ganttChartGridPane.getColumnConstraints().add(week);
        }

        ganttChartGridPane.add(new Label("Task"),0,0);
        // all the weeks add the week numbers

        LocalDate date = LocalDate.now();
        TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        int weekNumberToday = date.get(woy);

        for(int i = 1; i < (365/7)+1;i++){ //Adding week headers
            StackPane myStackPane = new StackPane(); //create a stackpane to add items (background color, and the label)
            GridPane.setFillHeight(myStackPane, true); //labels have a background colour but won't fill the cell.
            GridPane.setFillWidth(myStackPane, true);

            Label weekLabel = new Label("Week"+weekNumber);
            myStackPane.getChildren().add(weekLabel);
            if(weekNumber == weekNumberToday-1){

                myStackPane.setStyle("-fx-background-color:blue;"); //sets this week to blue
            }
            ganttChartGridPane.add(myStackPane,i,0);
            weekNumber++;
        }

        int tCounter=1;
        for (Task t: App.tasks) {
            RowConstraints taskRow = new RowConstraints(25); //height of row in pixels
            ganttChartGridPane.getRowConstraints().add(taskRow);
            ganttChartGridPane.add(new Label(t.getTaskName()), 0, tCounter);


            StackPane myStackPane = new StackPane(); //create a stackpane for the cell colour
            GridPane.setFillHeight(myStackPane, true); //the stackpane fills the grid cell
            GridPane.setFillWidth(myStackPane, true);

            String color = "";
            switch(t.getUrgency()) {  //get urgency value from Task class, color code based on urgency: 1-2 green, 3-4 yellow, 5 red

                case 1:
                case 2:
                    color = "-fx-background-color:green;";
                    break;
                case 3:
                case 4:
                    color = "-fx-background-color:yellow;";
                    break;
                case 5:
                    color = "-fx-background-color:red;";
                    break;
                default:
                    color = "-fx-background-color:white;";
            }

            myStackPane.setStyle(color);

            
            LocalDate taskDate = t.getDueDate();//THIS NEEDS TO BE A WEEK NUMBER OF YEAR
            TemporalField woy2 = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            int weekNumberToday2 = taskDate.get(woy2)-1;

            ganttChartGridPane.add(myStackPane,weekNumberToday2,tCounter);
            tCounter++;
            weekNumber++;

        }
    }

    @FXML
    private void switchToOrganization() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void switchToWelcomePage() throws IOException {
        App.setRoot("primary");
    }
}
