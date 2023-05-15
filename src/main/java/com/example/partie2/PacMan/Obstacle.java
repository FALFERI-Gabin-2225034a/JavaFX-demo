package com.example.partie2.PacMan;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
    public Obstacle(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setFill(Color.BLUE);
    }
}
