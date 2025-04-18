package edu.miracosta.cs112.ListAdventures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ShuttleResupplyMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListAdventures.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Space Shuttle Resupply!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}