package org.example;

// "Gantt chart" TAB

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.io.IOException;


public class FifthController {

    public GridPane ganttChart;

    public static void Initialize(){
        /**
         *
         * Table.addContent (taskName to column 0)
         *
         * For each Task t in tasks
         * 	Add Task name to First column
         * 	For each column in Table
         * 		If columnTitle == t.getGivenDate() AND columnTitle <= dueDate
         * 			Cell.colour = t.getDifficulty
         * 		endIf
         * 	End loop
         * EndFor
         *
         * For each row in Table
         * 	For each column in table
         * 		If cell == blue && columnTitle==ThisWeek
         * 			cell.Colour = BLACK
         * 		End if
         * 	End for
         * End for
         */
      //  week number  = 0;
        for(int i = 0; i < (12*4);i++){
            //add new column in gridview
            //add week number / date to top row of this column
            //week++

        }

        for (Task t: App.tasks) {
         //   ganttChart.add();
        }
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


}
