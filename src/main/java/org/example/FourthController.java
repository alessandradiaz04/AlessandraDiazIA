package org.example;

// "MY TASKS" TAB

import com.sun.glass.ui.Clipboard;
import com.sun.javafx.sg.prism.NGGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class FourthController {

    public TableView taskTable;
    private static ObservableList<Task> tasks = FXCollections.observableArrayList();
    public TableColumn taskColumn;
    public TableColumn urgencyColumn;
    public TableColumn courseColumn;
    public TableColumn givenDateColumn;
    public TableColumn dueDateColumn;
    public TableColumn completedColumn;

    private NGGroup root;
    ObservableList<Course> courses = FXCollections.observableArrayList();

    public void initialize() throws IOException {
        taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        urgencyColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("urgency"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("course"));
        givenDateColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("givenDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("dueDate"));
        completedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completedColumn));
        taskTable.setItems(tasks);
        taskTable.setEditable(true);

        taskColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        courseColumn.setCellFactory(ComboBoxTableCell.forTableColumn("Course1", "Course2", "Course3"));

    }


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    public void addTaskAction(ActionEvent actionEvent) {
        makeDialog();
    }


    public void makeCourseDialog() {

        Dialog<Task> dialog = new Dialog<>();
        dialog.initModality(Modality.NONE);
        Stage stage = (Stage) App.getScene().getWindow();
        dialog.initOwner(stage);

        //start making the stuff in the dialog
        dialog.setTitle("New Course");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label titleLabel = new Label("My title label");

        Label courseLabel = new Label("Course name");
        TextField courseNameBox = new TextField("");

        Label teacherLabel = new Label("Teacher");
        TextField teacherNameBox = new TextField("");


        //add all the labels and text fields etc...
        dialogPane.setContent(new VBox(titleLabel, courseLabel, courseNameBox, teacherLabel, teacherNameBox));
        //make an ok button
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        //Create what you want it to do when you click the button

        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if (!(courseNameBox.getText().equals("") && !teacherNameBox.getText().equals("")))
                    // if all your fields and things ARENT EMPTY
                    {
                        //read them all text fields and make a new object. Add it to your list of objects for the courses.
                        //courses.add(new Course(courseNameBox.getText(), teacherNameBox.getText()));

                        courses.add(new Course(courseNameBox.getText(), teacherNameBox.getText()));

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

    private void saveJson(ActionEvent actionEvent) {
    }


    public void makeDialog() {
        Dialog<Task> dialog = new Dialog<>();
        dialog.initModality(Modality.NONE);
        Stage stage = (Stage) App.getScene().getWindow();
        dialog.initOwner(stage);

        //start making the stuff in the dialog
        dialog.setTitle("Task Specifications");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label titleLabel = new Label("My title label");

        Label taskLabel = new Label("Task Title");
        TextField taskNameBox = new TextField("");

        Label urgencyLabel = new Label("Urgency");
        Slider slider = new Slider(1, 5, 1); // enable the marks
        slider.setShowTickMarks(true); // enable the Labels
        slider.setShowTickLabels(true); // set Major tick unit
        slider.setMajorTickUnit(1); // sets the value of the property
        // blockIncrement
        slider.setBlockIncrement(1);
        slider.isSnapToTicks();
        // display
        stage.show();


        Label courseLabel = new Label("Course");
        ChoiceBox courseChoiceBox = new ChoiceBox();

        courseChoiceBox.getItems().addAll(courses);
        courseChoiceBox.getItems().add("Create new course");

        System.out.println(courses);

        String value = (String) courseChoiceBox.getValue();
        courseChoiceBox.setOnAction((event) -> {
            if (courseChoiceBox.getSelectionModel().getSelectedIndex() == courseChoiceBox.getItems().size()-1) {
                makeCourseDialog();

                int selectedIndex = courseChoiceBox.getSelectionModel().getSelectedIndex();
                Object selectedItem = courseChoiceBox.getSelectionModel().getSelectedItem();

                //System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
                //System.out.println(" CourseChoiceBox.getValue(): " + courseChoiceBox.getValue());
                /////courseChoiceBox.getSelectionModel().
                courseChoiceBox.getItems().clear();
                courseChoiceBox.getItems().addAll(courses);
                courseChoiceBox.getItems().add("Create new course");
            }
        });


        Label givenDateLabel = new Label("Given Date");
        DatePicker datePicker = new DatePicker();

        Label dueDateLabel = new Label("Due Date");
        DatePicker datePickerTwo = new DatePicker();


        //add all the labels and text fields etc...
        dialogPane.setContent(new VBox(titleLabel, taskLabel, taskNameBox, urgencyLabel, slider, courseLabel, courseChoiceBox, givenDateLabel, datePicker, dueDateLabel, datePickerTwo));
        //make an ok button
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        //Create what you want it to do when you click the button
        System.out.println(slider.getValue());
        System.out.println(courseChoiceBox.getSelectionModel().getSelectedIndex());
        System.out.println(datePicker.getValue());

        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if (!(taskNameBox.getText().equals("") && !(courseChoiceBox.getSelectionModel().getSelectedIndex() < 0) && datePicker != null && datePickerTwo != null))// if all your fields and things ARENT EMPTY
                    {
                        LocalDate date = LocalDate.of(2022, Month.APRIL, 8);
                        //read them all text fields and make a new object. Add it to your list of objects for the courses.
                        tasks.add(new Task(taskNameBox.getText(), (int) slider.getValue(), (String) courseChoiceBox.getValue(), (LocalDate) datePicker.getUserData(), (LocalDate) datePickerTwo.getUserData()));
                        /**
                         try {
                         saveJson(new ActionEvent()); //try to save the json again so it keeps the new course.
                         } catch (IOException e) {
                         e.printStackTrace();
                         }
                         **/
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

    public void edit(ActionEvent event) {

    }


}








