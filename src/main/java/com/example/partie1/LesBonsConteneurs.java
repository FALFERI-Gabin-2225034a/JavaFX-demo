package com.example.partie1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LesBonsConteneurs extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Création du conteneur principal
        VBox vbox = new VBox();

        // Création du menu en haut de fenêtre
        HBox topControls = new HBox();
        topControls.setAlignment( Pos.BOTTOM_LEFT );

        //menu file
        MenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemClose;
        menuItemNew = new MenuItem("New");
        menuItemOpen = new MenuItem("Open");
        menuItemSave = new MenuItem("Save");
        menuItemClose = new MenuItem("Close");

        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(menuItemNew, new SeparatorMenuItem(), menuItemOpen, new SeparatorMenuItem(), menuItemSave,
                    new SeparatorMenuItem(), menuItemClose);

        //menu Edit
        MenuItem menuItemCut = new MenuItem("Cut");
        MenuItem menuItemCopy = new MenuItem("Copy");
        MenuItem menuItemPaste = new MenuItem("Paste");

        Menu menuEdit = new Menu("Edit");
        menuEdit.getItems().addAll(menuItemCut, new SeparatorMenuItem(), menuItemCopy, new SeparatorMenuItem(), menuItemPaste);

        Menu menuHelp = new Menu("Help");

        MenuBar menuBar = new MenuBar(menuFile, menuEdit, menuHelp);
        HBox.setHgrow(menuBar, Priority.ALWAYS);
        topControls.getChildren().add(menuBar);

        // Creation liste button
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER_LEFT);
        buttons.setMinWidth(75);
        buttons.setSpacing(10);

        Label legendeButton = new Label("Boutons :");
        HBox labelButton = new HBox(legendeButton);
        labelButton.setAlignment(Pos.CENTER);

        Button button1 = new Button("Bouton1");
        Button button2 = new Button("Bouton2");
        Button button3 = new Button("Bouton3");

        buttons.getChildren().addAll(
                labelButton,
                button1,
                button2,
                button3
        );

        // Creation formulaire
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);
        form.setPrefWidth(3000);
        form.setMaxWidth(Double.MAX_VALUE - buttons.getWidth());

        GridPane txtForm = new GridPane();
        txtForm.setAlignment(Pos.CENTER);
        txtForm.setVgap(10);
        txtForm.setHgap(10);

        TextField fieldName = new TextField();
        TextField fieldEmail = new TextField();
        TextField fieldPassword = new TextField();

        txtForm.add(new Label("Name :"), 0, 0);
        txtForm.add(fieldName, 1, 0);   // column=1, row=0
        txtForm.add(new Label("Email :"), 0, 1);
        txtForm.add(fieldEmail, 1, 1);
        txtForm.add(new Label("Password :"), 0, 2);
        txtForm.add(fieldPassword, 1, 2);

        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        HBox buttonsForm = new HBox(submit, cancel);
        buttonsForm.setAlignment(Pos.CENTER);
        buttonsForm.setSpacing(10);
        VBox.setMargin(buttonsForm, new Insets(10.0d, 0.0d, 0.0d,0.0d));

        form.getChildren().addAll(txtForm,buttonsForm);

        // Ajout liste boutons/formulaire
        HBox centre = new HBox();
        centre.setMinHeight(230);
        centre.setMinWidth(350);
        centre.setPrefHeight(3000);
        centre.setMaxHeight(Double.MAX_VALUE);
        centre.getChildren().addAll(buttons, new Separator(Orientation.VERTICAL), form);

        // Creation et ajout du bas de page
        VBox bottomControls = new VBox();
        bottomControls.setAlignment(Pos.BOTTOM_CENTER);
        bottomControls.getChildren().addAll(new Separator(Orientation.HORIZONTAL), new Label("Ceci est un label de bas de page"));

        // Ajout des contrôleurs au conteneur principal
        vbox.getChildren().addAll(
                topControls,
                centre,
                bottomControls
        );

        // Ajout du conteneur à la scene
        Scene scene = new Scene(vbox);

        // Ajout de la scene à la fenêtre et changement de ses paramètres (dimensions et titre)
        primaryStage.setScene( scene );
        primaryStage.setMinWidth(centre.getMinWidth());
        primaryStage.setMinHeight(centre.getMinHeight() + 100);
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