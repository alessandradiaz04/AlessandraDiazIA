package org.example;

// MINDFULNESS TAB


import java.io.IOException;
import javafx.fxml.FXML;

public class ThirdController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");

    }



    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");

    }

}