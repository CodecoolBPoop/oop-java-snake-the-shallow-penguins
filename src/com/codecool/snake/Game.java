package com.codecool.snake;

import com.codecool.snake.entities.enemies.RandomEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SquareEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        spawnSimpleEnemies(1);
        spawnRandomEnemies(1);
        spawnSquareEnemies(6);
        spawnPowerUps(4);

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
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
}
