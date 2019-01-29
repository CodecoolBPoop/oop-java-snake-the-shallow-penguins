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

    private Point2D heading;
    private static Random rnd = new Random();

    // distance travelled
    private int travelled;

    public SquareEnemy() {
        super(10);
        travelled = 0;

        setImage(Globals.getInstance().getImage("SquareEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = rnd.nextInt(4) * 90;
        setRotate(direction);


        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        travelled += Math.abs(heading.getX()) + Math.abs(heading.getY());
        System.out.println(travelled);
        if (travelled % 90 == 0) {
            double direction = travelled;
            heading = Utils.directionToVector(direction, 1);
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
