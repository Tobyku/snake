package com.snake;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game(){
        setTitle("Snake");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();
    }

    public void initialize(){
        setLayout(new GridLayout(1, 1, 0, 0));

        Window w = new Window();
        add(w);

        pack();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args){

        new Game();
    }
}
