package com.snake;

import java.awt.*;

public class Snake {
    Color snakeColor;
    private int x, y, width, height;

    public Snake(int x, int y, int tailThick){
        this.x = x;
        this.y = y;
        width = tailThick;
        height = tailThick;
    }

    public void draw(Graphics g){
        snakeColor = new Color(0, 100, 0);
        g.setColor(snakeColor);
        g.fillRect(x * width, y * height, width, height);
        g.fillRect(x * width + 2, y * height + 2, width - 4, height - 4);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
