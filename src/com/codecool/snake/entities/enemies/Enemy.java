package com.codecool.snake.entities.enemies;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Enemy extends GameEntity{
    private final int damage;
    protected Point2D heading;
    protected int travelled;
    Random rnd = new Random();

    protected double direction;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void bounceBack() {
        heading = Utils.directionToVector(direction + rnd.nextInt(180) + 90, rnd.nextInt(3) + 1);;
    }
}
