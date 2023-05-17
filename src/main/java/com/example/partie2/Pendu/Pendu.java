package com.example.partie2.Pendu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Pendu extends Application {
    private Dico dico = new Dico();
    private String motADecouvrir = dico.getMot();
    private Label pendu;
    private int vies = 7;
    private Label labelVies;
    private Label motCache;
    private boolean aGagne = false;

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        // ajout d'un fond bleu clair
        borderPane.setStyle("-fx-background-color: #bcf6e2;");

        // Creation du centre de la page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);


        // Image du pendu
        ImageView imgPendu = new ImageView();
        imgPendu.setImage(new Image(Pendu.class.getResource("pendu7.png").toString()));
        pendu = new Label();
        pendu.setGraphic(imgPendu);
        // Nombre de vies
        labelVies = new Label("Nombre de vies : " + vies);
        // Creation mot Cache
        String strMotCache = "";
        for (int i = 0; i < motADecouvrir.length(); ++i) strMotCache += "_";
        motCache = new Label(strMotCache);
        // Boutons lettres
        final String ALPHABET = "aeiouybcdfghjklmnpqrstvwxz";
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < ALPHABET.length(); i++) {
            Button button = new Button(Character.toString(ALPHABET.charAt(i)));
            button.setOnAction(actionEvent -> appuieLettre(actionEvent));
            if (i < 6) {
                button.setStyle("-fx-background-color: #bcf6e2; -fx-border-color: #ff8a37; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-text-fill: #4da888; -fx-font-size: 14px;");
                gridPane.add(button, i+2, 0);
            }
            else if (i >= 6 && i < 16) {
                button.setStyle("-fx-background-color: #bcf6e2; -fx-border-color: #ff8a37; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-text-fill: #4da888; -fx-font-size: 14px;");
                gridPane.add(button, (i-6)%10, 1);
            }
            else {
                button.setStyle("-fx-background-color: #bcf6e2; -fx-border-color: #ff8a37; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px; -fx-text-fill: #4da888;");
                gridPane.add(button, (i-6)%10, 2);
            }
        }

        // Bouton rejouer
        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        Button rejouer = new Button("Rejouer");
        rejouer.setStyle("-fx-background-color: #bcf6e2; -fx-border-color: #ff8a37; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-font-size: 14px; -fx-text-fill: #4da888;");
        rejouer.setOnAction(actionEvent -> appuieRejouer(actionEvent, gridPane));
        bottom.getChildren().add(rejouer);

        // Ajout des élements du centre
        center.getChildren().addAll(
                pendu,
                labelVies,
                motCache,
                gridPane
        );

        borderPane.setCenter(center);

        // Ajout du bas
        borderPane.setBottom(bottom);

        // Création de la scène
        Scene scene = new Scene(borderPane, 400, 500);

        // Affichage de la scène
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jeu du pendu");
        primaryStage.show();
    }

    public void appuieLettre(ActionEvent event) {
        if (vies > 0 && !aGagne) {
            Button target = (Button) event.getSource();
            target.setDisable(true); // désactive le bouton
            Character lettre = target.getText().charAt(0);
            ArrayList<Integer> positions = dico.getPositions(lettre, motADecouvrir);
            if (positions.isEmpty()) {
                vies--;
                labelVies.setText("Nombre de vies : " + vies);
                ImageView imgPendu = new ImageView();
                imgPendu.setImage(new Image(Pendu.class.getResource("pendu" + vies + ".png").toString()));
                pendu.setGraphic(imgPendu);
            } else {
                StringBuilder sb = new StringBuilder(motCache.getText());
                for (Integer position : positions) {
                    sb.setCharAt(position, lettre);
                }
                motCache.setText(sb.toString());
            }
            if (motEstTrouve(motCache.getText())) {
                ImageView imgPendu = new ImageView();
                imgPendu.setImage(new Image(Pendu.class.getResource("penduWin.png").toString()));
                pendu.setGraphic(imgPendu);
            }
        }
    }

    public boolean motEstTrouve(String mot) {
        for (int i = 0; i < mot.length(); ++i) {
            if (mot.charAt(i) == '_') return false;
        }
        aGagne = true;
        return true;
    }

    public void appuieRejouer(ActionEvent actionEvent, GridPane gridPane) {
        ImageView imgPendu = new ImageView();
        imgPendu.setImage(new Image(Pendu.class.getResource("pendu7.png").toString()));
        pendu.setGraphic(imgPendu);

        vies = 7;
        labelVies.setText("Nombre de vies : " + vies);
        motADecouvrir = dico.getMot();
        aGagne = false;

        String strMotCache = "";
        for (int i = 0; i < motADecouvrir.length(); ++i) strMotCache += "_";
        motCache.setText(strMotCache);

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(false);
            }
        }
    }
}