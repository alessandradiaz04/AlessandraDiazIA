
package org.example;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Scene scene;
    public static ObservableList<Task> tasks = FXCollections.observableArrayList();  //Allow all classes to have access to the Tasks List
    public static ObservableList<Course> courses = FXCollections.observableArrayList(); //Allow all classes to have access to the Courses List
    public static ObservableList <Event> events = FXCollections.observableArrayList(); //Allow all classes to have access to the Events List

    public App() { }

    public static Scene getScene() {
        return scene;
    }

    public void start(Stage primaryStage) throws IOException {
        loadTasks();
        scene = new Scene(loadFXML("primary"));
        primaryStage.setTitle("Welcome Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadTasks() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("tasks.Json")) { //Read content from Tasks.pomxml file
            ArrayList<Task> imports = gson.fromJson(reader, new TypeToken<ArrayList<Task>>() {
            }.getType());
            App.tasks = FXCollections.observableArrayList(imports); //import data
        } catch (IOException e) {
            e.printStackTrace();
        }
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
