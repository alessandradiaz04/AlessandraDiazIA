package org.example;

// MINDFULNESS TAB


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import javafx.stage.Stage;

public class ThirdController {

    public TableView eventTable;
    public TableColumn eventColumn;
    public TableColumn eventDateColumn;

    public void initialize() throws IOException {
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        eventTable.setItems(App.events);
        eventTable.setEditable(true);
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void addEventAction (ActionEvent actionEvent) {
        makeDialog();

        Dialog<Task> dialog = new Dialog<>();
        dialog.initModality(Modality.NONE);
        Stage stage = (Stage) App.getScene().getWindow();
        dialog.initOwner(stage);

        //start making the stuff in the dialog
        dialog.setTitle("New Event");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label titleLabel = new Label("My title label");

        Label eventLabel = new Label("Event Name");
        TextField eventNameBox = new TextField("");

        Label eventDateLabel = new Label("Event Date");
        DatePicker datePickerEvent = new DatePicker();


        //add all the labels and text fields etc...
        dialogPane.setContent(new VBox(titleLabel, eventLabel, eventNameBox, eventDateLabel, datePickerEvent));
        //make an ok button
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        //Create what you want it to do when you click the button

        btOk.addEventFilter(ActionEvent.ACTION, event -> {
            if (!(eventNameBox.getText().equals("") && datePickerEvent != null))
            // if all your fields and things ARENT EMPTY
            {
                //read them all text fields and make a new object. Add it to your list of objects for the courses.

                App.events.add(new Event(eventNameBox.getText(), (LocalDate) datePickerEvent.getValue()));

            } else { //else if some text field is empty or incorrect. give them an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect input");
                alert.setHeaderText(null);
                alert.setContentText("Make sure everything is filled in correctly.");
                alert.showAndWait();
                event.consume(); //consume the ok button event so it doesn't close the dialog.
            }

        });
    }

    public void makeDialog() {
    }

    @FXML
    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        System.out.println("Link clicked!");
        Desktop.getDesktop().browse(new URI("https://pomofocus.io/"));
    }
}



// thank you for checking in! dialogue box after pressing emoticon
// activity output after scale
