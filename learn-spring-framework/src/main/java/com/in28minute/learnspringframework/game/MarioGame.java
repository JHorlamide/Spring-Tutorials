package com.in28minute.learnspringframework.game;

public class MarioGame implements GamingConsole {
    public void up() {
        System.out.println("Jump up");
    }

    public void down() {
        System.out.println("Go down");
    }

    public void left() {
        System.out.println("Go left");
    }

    public void right() {
        System.out.println("Go right");
    }
}
