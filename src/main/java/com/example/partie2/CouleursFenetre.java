package com.example.partie2;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.annotation.Target;

public class CouleursFenetre extends Application {
    // Label affiche le message des couleurs
    private Label label;
    // Affiche la couleur
    private Pane pane;
    // Bouttons couleurs
    private Button vert;
    private Button bleu;
    private Button rouge;

    // Compteurs nbr de click
    private int cmptVert;
    private int cmptBleu;
    private int cmptRouge;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création d'un conteneur VBox avec ses éléments centrés
        BorderPane borderPane = new BorderPane();

        // Création Label
        HBox top = new HBox();
        top.setAlignment( Pos.CENTER);
        label = new Label();
        label.setStyle("-fx-font-size: 20px;");

        top.getChildren().add(label);
        borderPane.setTop(top);
        // Init Pane
        pane = new Pane();
        borderPane.setCenter(pane);

        // Création Button
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        vert = new Button("Vert");
        bleu = new Button("Bleu");
        rouge = new Button("Rouge");

        buttons.getChildren().addAll(vert, bleu, rouge);
        borderPane.setBottom(buttons);
        buttons.setSpacing(10);
        BorderPane.setMargin(buttons, new Insets(10)); // add bottom margin

        // Event couleurs
        vert.setOnAction(actionEvent -> handleButonClick(actionEvent) );
        bleu.setOnAction(actionEvent -> handleButonClick(actionEvent) );
        rouge.setOnAction(actionEvent -> handleButonClick(actionEvent) );

        // Création de la scene
        Scene scene = new Scene( borderPane, 400, 200 );

        // Ajout de la scene à la fenêtre
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    // Actions effectuées lors du clic sur le bouton
    private void handleButonClick(Event event) {
        Button target = (Button) event.getSource();
        if (target.getText() == "Vert") {
            pane.setStyle("-fx-background-color: green");
            ++cmptVert;
            label.setText("Vert choisi " + cmptVert + " fois");
        }
        else if (target.getText() == "Bleu") {
            pane.setStyle("-fx-background-color: blue");
            ++cmptBleu;
            label.setText("Bleu choisi " + cmptBleu + " fois");
        }
        else if (target.getText() == "Rouge") {
            pane.setStyle("-fx-background-color: red");
            ++cmptRouge;
            label.setText("Rouge choisi " + cmptRouge + " fois");
        }
    }
}
