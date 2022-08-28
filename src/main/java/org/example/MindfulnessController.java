package org.example;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MindfulnessController<stressMessage> {

    public TableView eventTable;
    public TableColumn eventColumn;
    public TableColumn eventDateColumn;
    public Slider stressSlider;

    public ImageView myImageViewOne;
    public ImageView myImageViewTwo;
    public ImageView myImageViewThree;
    public ImageView myImageViewFour;
    public ImageView myImageViewFive;
    public ImageView myImageViewSix;

    public void initialize() throws IOException {
        myImageViewOne.setImage(new Image(getClass().getResourceAsStream("/images/happy.png")));
        myImageViewTwo.setImage(new Image(getClass().getResourceAsStream("/images/stressed.png")));
        myImageViewThree.setImage(new Image(getClass().getResourceAsStream("/images/confused.png")));
        myImageViewFour.setImage(new Image(getClass().getResourceAsStream("/images/sad.png")));
        myImageViewFive.setImage(new Image(getClass().getResourceAsStream("/images/frustrated.png")));
        myImageViewSix.setImage(new Image(getClass().getResourceAsStream("/images/mad.png")));
        eventColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));

        eventTable.setItems(App.events);
        eventTable.setEditable(true);
    }
    @FXML
    private void switchToWelcomePage() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    private void switchToOrganization() throws IOException {
        App.setRoot("secondary");
    }

    public void addEventAction () {
        makeDialog();
        Dialog<Task> dialog = new Dialog<>();
        dialog.initModality(Modality.NONE);
        Stage stage = (Stage) App.getScene().getWindow();
        dialog.initOwner(stage);
        //start making the stuff in the dialog
        dialog.setTitle("New Event");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label titleLabel = new Label("Create a new Event");

        Label eventLabel = new Label("Event Name");
        TextField eventNameBox = new TextField("");

        Label eventDateLabel = new Label("Event Date");
        DatePicker datePicker = new DatePicker();

        
        //add all the labels and text fields etc...
        dialogPane.setContent(new VBox(titleLabel, eventLabel, eventNameBox, eventDateLabel, datePicker));
        //make an ok button
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        //Create what you want it to do when you click the button

        btOk.addEventFilter(ActionEvent.ACTION, event -> {
            if (!(eventNameBox.getText().equals("") && datePicker != null))
            // if all your fields and things ARENT EMPTY
            {
                //read them all text fields and make a new object. Add it to your list of objects for the courses.

                App.events.add(new Event(eventNameBox.getText(), (datePicker.getValue())));

            } else { //else if some text field is empty or incorrect. give them an error message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect input");
                alert.setHeaderText(null);
                alert.setContentText("Make sure everything is filled in correctly.");
                alert.showAndWait();
                event.consume(); //consume the ok button event so it doesn't close the dialog.
            }

        });
        Optional<Task> optionalResult = dialog.showAndWait(); //show the dialog.

    }

    public void makeDialog() {
    }

    @FXML
    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        System.out.println("Link clicked!");
        Desktop.getDesktop().browse(new URI("https://pomofocus.io/"));
    }

    public void MyStressAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Stress-o-meter");
        alert.setHeaderText(null);
        int stressSliderAmount = (int)stressSlider.getValue();

        if (stressSliderAmount <=100 && stressSliderAmount>70){
            alert.setContentText ("You seem very stressed! Try going for a walk, enjoy nature and then get back on track. " + "Stress Level: "+ stressSliderAmount);
            alert.showAndWait();
        } else if (stressSliderAmount <69 && stressSliderAmount>30) {
            alert.setContentText ("You seem kinda stressed. Listen to some calming music! " + "Stress Level: " + stressSliderAmount);
            alert.showAndWait();
        } else {
            alert.setContentText ("You are doing great! Keep going! " + "Stress Level: "+ stressSliderAmount);
            alert.showAndWait();
        }
    }
    }

