package com.example.partie2.PacMan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class JeuMain extends Application {

    private Scene scene;
    private BorderPane root;
    protected static ArrayList<Obstacle> listObstacles = new ArrayList<>();
    private int timerCount = 20; // temps en secondes
    private Text timerText = new Text(Integer.toString(timerCount));
    private boolean winPacman = false;
    private boolean winFantome = false;

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();

        //Acteurs du jeu
        Personnage pacman = new Pacman();
        Personnage fantome = new Fantome();
        // Obstacles
        listObstacles.add(new Obstacle(90, 90, 20, 200));
        listObstacles.add(new Obstacle(90, 330, 160, 20));
        listObstacles.add(new Obstacle(410, 30, 20, 300));
        listObstacles.add(new Obstacle(350, 30, 120, 20));
        listObstacles.add(new Obstacle(290, 210, 40, 40));
        // on positionne le fantôme dans l'angle inférieur droit
        fantome.setLayoutX(620);
        fantome.setLayoutY(460);
        //panneau du jeu
        Pane jeu = new Pane();
        jeu.setPrefSize(640, 480);
        jeu.getChildren().add(pacman);
        jeu.getChildren().add(fantome);
        for (Obstacle obstacle : listObstacles) {
            jeu.getChildren().add(obstacle);
        }
        root.setCenter(jeu);

        // Ajout du timer en haut à droite de l'écran
        timerText.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        timerText.setFill(Color.BLACK);
        BorderPane.setAlignment(timerText, Pos.TOP_RIGHT);
        root.setTop(timerText);

        //on construit une scene 640 * 480 pixels
        scene = new Scene(root);

        //Gestion du déplacement du personnage
        deplacer(pacman, fantome);

        primaryStage.setTitle("... Pac Man ...");

        primaryStage.setScene(scene);
        primaryStage.show();

        // Lancement du timer
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    timerCount--;
                    if (timerCount <= 0 && !winFantome) {
                        // Si le timer arrive à 0, pacman gagne
                        timerText.setFill(Color.WHITE);
                        Text gameOver = new Text("Pacman a gagné !");
                        gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
                        gameOver.setFill(Color.RED);
                        BorderPane.setAlignment(gameOver, Pos.CENTER);
                        root.setCenter(gameOver);
                        winPacman = true;
                    }
                    timerText.setText(Integer.toString(timerCount));
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Permet de gérer les événements de type clavier, pression des touches
     * pour le j1(up,down, right, left), pour le j2( z,q,s,d)
     *
     * @param j1
     * @param j2
     */
    private void deplacer(Personnage j1, Personnage j2) {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    j1.deplacerAGauche();
                    break;
                case RIGHT:
                    j1.deplacerADroite(scene.getWidth());
                    break;
                case UP:
                    j1.deplacerEnHaut();
                    break;
                case DOWN:
                    j1.deplacerEnBas(scene.getHeight());
                    break;
                case Q:
                    j2.deplacerAGauche();
                    break;
                case D:
                    j2.deplacerADroite(scene.getWidth());
                    break;
                case Z:
                    j2.deplacerEnHaut();
                    break;
                case S:
                    j2.deplacerEnBas(scene.getHeight());
                    break;
            }
            if (j1.estEnCollision(j2) && !winPacman) {
                winFantome = true;
                Text gameOver = new Text("Le fantôme à gagner");
                gameOver.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
                gameOver.setFill(Color.RED);
                BorderPane.setAlignment(gameOver, Pos.CENTER);
                root.setCenter(gameOver);
                timerText.setFill(Color.WHITE);
            }
        });
    }
}
