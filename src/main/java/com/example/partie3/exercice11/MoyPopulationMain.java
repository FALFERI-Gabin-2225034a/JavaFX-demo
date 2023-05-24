package com.example.partie3.exercice11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MoyPopulationMain extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MoyPopulationView.fxml"));
        MoyPopulationController controller = new MoyPopulationController();
        loader.setController(controller);

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(800);
        primaryStage.setTitle("Whale App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}