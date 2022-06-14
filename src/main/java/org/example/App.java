//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Scene scene;
    public static ObservableList<Task> tasks = FXCollections.observableArrayList();
    public static ObservableList<Course> courses = FXCollections.observableArrayList();
    public static ObservableList <Event> events = FXCollections.observableArrayList();;

    public App() {
    }

    public static Scene getScene() {
        return scene;
    }

    public void start(Stage primaryStage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        primaryStage.setTitle("Welcome Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return (Parent)fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
