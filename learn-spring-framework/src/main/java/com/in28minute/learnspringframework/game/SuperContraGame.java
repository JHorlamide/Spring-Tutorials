package com.in28minute.learnspringframework.game;

public class SuperContraGame implements  GamingConsole {
    public void up() {
        System.out.println("Go up");
    }

    public void down() {
        System.out.println("Sid down");
    }

    public void left() {
        System.out.println("Change direction to left");
    }

    public void right() {
        System.out.println("Change direction to right");
    }
}
