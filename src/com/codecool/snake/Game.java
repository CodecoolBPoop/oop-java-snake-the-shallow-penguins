package com.codecool.snake;

import com.codecool.snake.entities.enemies.RandomEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SquareEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();

    private Button restartBtn = new Button("Restart");


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();

    }

    public void init() {
        spawnSnake();
        spawnSimpleEnemies(10);
        spawnRandomEnemies(4);
        spawnSquareEnemies(4);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();

        getChildren().add(restartBtn);
        addButtonsEventHandlers();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnSimpleEnemies(int numberOfSimpleEnemies) {
        for(int i = 0; i < numberOfSimpleEnemies; ++i) new SimpleEnemy();
    }

    private void spawnRandomEnemies(int numberOfRandomEnemies) {
        for(int i = 0; i < numberOfRandomEnemies; ++i) new RandomEnemy();
    }

    private void spawnSquareEnemies(int numberOfSquareEnemies) {
        for(int i = 0; i < numberOfSquareEnemies; ++i) new SquareEnemy();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }

    public void addButtonsEventHandlers() {
        restartBtn.setOnAction((event -> restartGame()));
    }

    public void restartGame() {

//        this.getChildren().clear();

        Globals.getInstance().display.clear();
//        this.getChildren().removeAll();
//
//        for (int i = 0; i < getChildren().size(); i++) {
//            getChildren().get(i).
//        }



        System.out.println(this.getChildren());

        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        init();
        start();
    }


}
