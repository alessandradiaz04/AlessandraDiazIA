package org.example;

// "MY TASKS" TAB

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class FourthController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void switchToPrimary() throws IOException {

}
    @FXML
    private void clickOpenWindow(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println("it works");
        
        }
     }
     }




