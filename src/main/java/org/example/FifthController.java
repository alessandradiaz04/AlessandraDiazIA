package org.example;

// "Gantt chart" TAB

import javafx.fxml.FXML;

import java.io.IOException;

public class FifthController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
