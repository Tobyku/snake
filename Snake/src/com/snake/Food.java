package com.snake;

import java.awt.*;
import java.util.Random;

public class Food {

    private int x, y, width, height;
    private float r, g, b;

    private Random rand;
    private Color foodColor;

    public Food(int x, int y, int foodThick){
        this.x = x;
        this.y = y;
        width = foodThick;
        height = foodThick;
    }

    public void draw(Graphics gr){
        rand = new Random();

        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();

        foodColor = new Color(r, g, b);

        gr.setColor(Color.BLACK);
        gr.fillOval(x * width, y * height, width, height);
        gr.setColor(foodColor);
        gr.fillOval(x * width + 1, y * height + 1, width - 2, height - 2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
