package org.example;

// ORGANIZATION TAB

import java.awt.*;
import java.io.IOException;
import java.util.EnumSet;

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

    @FXML
    private void switchToFifth() throws IOException {
        App.setRoot("fifth");
    }

    @FXML
    private void switchToThird() throws IOException {
        App.setRoot("third");

    }
}
