package com.codecool.snake;


import java.util.Random;

public class RandomSnake {

    public String[] SnakeBodies = {
            "snake_body.png", "snake_body1.png", "snake_body2.png", "snake_body3.png", "snake_body4.png", "snake_body5.png",
            "snake_body6.png", "snake_body7.png", "snake_body8.png", "snake_body9.png", "snake_body10.png", "snake_body11.png",
            "snake_body12.png", "snake_body13.png", "snake_body14.png", "snake_body15.png", "snake_body16.png",
            "snake_body17.png", "snake_body18.png", "snake_body19.png", "snake_body20.png"};

    public String randomPicture = SnakeBodies[new Random().nextInt(SnakeBodies.length)];

    public String getRandomPicture(){
        return randomPicture;
    }

}
