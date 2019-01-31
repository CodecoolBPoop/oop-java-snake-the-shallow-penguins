package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.RandomEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SquareEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.Iterator;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();

    private Button restartBtn = new Button("Restart");


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();

        displayHealth();
    }

    public void init() {

        BackgroundImage myBI= new BackgroundImage(new Image("bg.jpg",1450,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        this.setBackground(new Background(myBI));

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

    public void displayHealth(){


        Text t = new Text (100,60, "" + snake.getHealth());
        t.setFont(Font.font ("Verdana", FontWeight.BOLD, 32));
        t.setFill(Color.rgb(250,118,114));

        Text healthNum = new Text( 10, 60, "Health: ");
        healthNum.setFont(Font.font ("Fraktur", FontWeight.EXTRA_BOLD, 22));
        healthNum.setFill(Color.rgb(70,128,40));


        for (Node node : getChildren()
             ) {
//            System.out.println(node.toString());
            if (node instanceof Text){
                getChildren().remove(node);
            }
        }

        getChildren().add(healthNum);
        getChildren().add(t);
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

    void cleanUp() {
        Iterator snakeBody = this.snake.body.getList().iterator();
        Iterator sprite = this.getChildren().iterator();

        while (snakeBody.hasNext()) {
            ((GameEntity) snakeBody.next()).destroy();
        }
        this.snake.body.clear();

        while (sprite.hasNext()) {
            ((GameEntity) sprite.next()).destroy();
        }
//        this.snake.health = 0;
//        this.snake.speed = 2;
//        this.snake.life.resetHealth(this.snake.startingHealth);
    }

    void setSnake() {
        this.snake.head.setX(500);
        this.snake.head.setY(500);
        this.snake.addPart(4);

        this.snake.head.setRotate(0);
        Globals.getInstance().startGame();

        spawnSimpleEnemies(10);
        spawnRandomEnemies(4);
        spawnSquareEnemies(4);
        spawnPowerUps(4);
    }


    public void restartGame() {
//        cleanUp();
//        setSnake();

        this.getChildren().clear();
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();

        displayHealth();
        start();
    }


}
