package com.example.partie1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création du conteneur principal
        GridPane grille = new GridPane();
        grille.setGridLinesVisible(true);

        // Création image
        ArrayList<ImageView> ListeIV = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {ListeIV.add(new ImageView());};

        ArrayList<Label> ListeLabel = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {ListeLabel.add(new Label());};

        // Pacage des images
        Random random = new Random();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                int nombre = random.nextInt(3);
                switch (nombre) {
                    case 1:
                        ListeIV.get(i*3+j).setImage(new Image(TicTacToe.class.getResource("Croix.png").toString()));
                        break;
                    case 2:
                        ListeIV.get(i*3+j).setImage(new Image(TicTacToe.class.getResource("Rond.png").toString()));
                        break;
                    case 3:
                        ListeIV.get(i*3+j).setImage(new Image(TicTacToe.class.getResource("Vide.png").toString()));
                        break;
                    default:
                        break;
                }
                ListeLabel.get(i*3+j).setGraphic(ListeIV.get(i*3+j));
                grille.add(ListeLabel.get(i*3+j), i, j);
            }
        }

        // Ajout du conteneur à la scene
        Scene scene = new Scene(grille);

        // Ajout de la scene à la fenêtre et changement de ses paramètres (dimensions et titre)
        primaryStage.setScene( scene );
        primaryStage.setResizable(false);
        primaryStage.setTitle("Tic Tac Toe");

        // Affichage de la fenêtre
        primaryStage.show();
    }
}