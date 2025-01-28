package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/HomeView/LoginPage.fxml"));
        Scene scene = new Scene(load);
        stage.setTitle("CrystalWave Hotel Login");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}