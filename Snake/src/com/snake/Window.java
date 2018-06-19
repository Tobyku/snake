package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Window extends JPanel implements Runnable{

    public static int width = 495;
    public static int height = 495;
    public int speed = 1000000;

    private boolean isRunning = false, isPause = false, isGameOver = false;
    private Thread thread;

    private Random r;

    private Snake s;
    private ArrayList<Snake> snake;

    private Food f;
    private ArrayList<Food> food;

    private Font pauseFont;

    private int x = 15;
    private int y = 15;
    private int snakeLong = 4;

    private boolean up = false, down = true, right = false, left = false;

    private int ref = 0;

    private Key key;
    public Window(){
        setFocusable(true);
        key = new Key();
        addKeyListener(key);
        setPreferredSize(new Dimension(width, height));

        r = new Random();

        snake = new ArrayList<>();
        food = new ArrayList<>();

        start();
    }

    public void refresh(){
        if(snake.size() == 0){
            s = new Snake(x, y, 15);
            snake.add(s);
        }

        if(food.size() == 0){
            int x = r.nextInt(32);
            int y = r.nextInt(32);

            f = new Food(x, y, 15);
            food.add(f);
        }

        for (int i = 0; i < food.size(); i++){
            if(x == food.get(i).getX() && y == food.get(i).getY()){
                java.awt.Toolkit.getDefaultToolkit().beep();
                snakeLong++;
                food.remove(i);
                i--;
            }
        }

        for(int i = 0; i < snake.size(); i++){
            if(x == snake.get(i).getX() && y == snake.get(i).getY()){
                if(i != snake.size() - 1){
                    isGameOver = true;
                    stop();
                }
            }
        }

        if(x < 0){ x = 32; }
        if(x > 32){ x = 0; }
        if(y < 0){ y = 32; }
        if(y > 32){ y = 0; }

        ref++;

        switch (snake.size()) {
            case 10:
                speed = 900000;
            case 20:
                speed = 800000;
            case 25:
                speed = 700000;
            case 30:
                speed = 600000;
        }

        if(ref > speed){
            if(up) y--;
            if(down) y++;
            if(right) x++;
            if(left) x--;

            ref = 0;
            s = new Snake(x, y, 15);
            snake.add(s);

            if(snake.size() > snakeLong){
                snake.remove(0);
            }
        }
    }

    public void paint(Graphics g){
        g.clearRect(0, 0, width, height);

        for (int i = 0; i < width / 15; i++){
            g.drawLine(i * 15, 0, i * 15, height);
        }

        for (int i = 0; i < height / 15; i++){
            g.drawLine(0, i * 15, width, i * 15);
        }

        for(int i = 0; i < snake.size(); i++){
            snake.get(i).draw(g);
        }

        for (int i = 0; i < food.size(); i++){
            food.get(i).draw(g);
        }

        if(isPause){
            pause(g);
        }

        if(isGameOver){
            gameOver(g);
        }
    }

    public void start(){
        isRunning = true;
        thread = new Thread(this, "Game Running");
        thread.start();
    }

    public void stop(){
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(isRunning){
            refresh();
            repaint();
        }
    }

    public void pause(Graphics g){
        pauseFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(pauseFont);
        g.setColor(Color.RED);
        g.drawString("Pause", (width/2) - 65, (height/2) - 50);
        pauseFont = new Font("Arial", Font.BOLD, 30);
        g.setFont(pauseFont);
        g.drawString("Space - Resume", (width/2) - 110, (height/2) - 15);
        g.drawString("ESC - Game Over", (width/2) - 117, (height/2) + 20);
    }

    public void gameOver(Graphics g){
        pauseFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(pauseFont);
        g.setColor(Color.RED);
        g.drawString("Game Over", (width/2) - 135, height/2);
    }

    private class Key implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && !left){
                up = false;
                down = false;
                right = true;
            }
            if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && !right){
                up = false;
                down = false;
                left = true;
            }
            if((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && !down){
                right = false;
                left = false;
                up = true;
            }
            if((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && !up){
                right = false;
                left = false;
                down = true;
            }

            if(key == KeyEvent.VK_ESCAPE){
                if(isRunning){
                    isPause = true;
                    stop();
                }
                else{
                    System.exit(0);
                }
            }

            if( key == KeyEvent.VK_SPACE){
                if(!isRunning){
                    isPause = false;
                    start();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
