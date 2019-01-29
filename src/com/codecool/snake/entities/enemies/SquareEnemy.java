package com.codecool.snake.entities.enemies;

import com.codecool.snake.GameTimer;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;



public class SquareEnemy extends Enemy implements Animatable, Interactable {


    public SquareEnemy() {
        super(10);
        travelled = rnd.nextInt(360);

        setImage(Globals.getInstance().getImage("SquareEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = rnd.nextInt(4) * 90;
        setRotate(direction);


        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {

        if (isOutOfBounds()) {
//            destroy();
            heading = Utils.directionToVector(direction + 180, rnd.nextInt(3) + 1);
        }

        travelled += Math.abs(heading.getX()) + Math.abs(heading.getY());
        System.out.println(travelled);
        if (travelled % 90 == 0) {
            double direction = travelled;
            heading = Utils.directionToVector(direction, rnd.nextInt(4) + 1);
        }


        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
