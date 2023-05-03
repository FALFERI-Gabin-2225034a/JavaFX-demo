package com.example.partie1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création du conteneur principal
        GridPane grille = new GridPane();

        // Ajout du conteneur à la scene
        Scene scene = new Scene(grille);

        // Ajout de la scene à la fenêtre et changement de ses paramètres (dimensions et titre)
        primaryStage.setScene( scene );
        primaryStage.setWidth( 800 );
        primaryStage.setHeight( 600 );
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle("Tic Tac Toe");

        // Affichage de la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}