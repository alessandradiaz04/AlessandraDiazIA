package org.example;

// ORGANIZATION TAB

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToFourth() throws IOException {
        App.setRoot("fourth");
    }
}