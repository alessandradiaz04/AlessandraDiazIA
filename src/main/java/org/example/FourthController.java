package org.example;

// "MY TASKS" TAB

import javafx.fxml.FXML;

import java.io.IOException;

public class FourthController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
