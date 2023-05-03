package com.example.partie1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Ajout du conteneur à la scene
        Scene scene = new Scene(vbox);

        // Ajout de la scene à la fenêtre et changement de ses paramètres (dimensions et titre)
        primaryStage.setScene( scene );
        primaryStage.setWidth( 800 );
        primaryStage.setHeight( 600 );
        primaryStage.setTitle("Premier Exemple manipulant les conteneurs");

        // Affichage de la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}