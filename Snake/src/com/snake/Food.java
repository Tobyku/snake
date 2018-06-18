package com.snake;

import java.awt.*;

public class Food {

    private int x, y, width, height;

    public Food(int x, int y, int foodThick){
        this.x = x;
        this.y = y;
        width = foodThick;
        height = foodThick;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillOval(x * width, y * height, width, height);
        g.setColor(Color.green);
        g.fillOval(x * width + 1, y * height + 1, width - 2, height - 2);
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
