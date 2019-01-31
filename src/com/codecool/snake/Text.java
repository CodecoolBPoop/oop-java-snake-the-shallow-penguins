package com.codecool.snake;

import com.codecool.snake.entities.snakes.Snake;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;


public class Text extends Pane {

    public static int player1Score;


    /*public static void updateScoreDisplay(Text scoreText, String scoreString, int score) {
        scoreText.setText(scoreString + String.valueOf(score));
    }
*/
    public static void adjustScores(Snake snake) {
        player1Score = snake.getScore();
    }
}

