package com.in28minute.learnspringframework;

import com.in28minute.learnspringframework.game.GameRunner;
import com.in28minute.learnspringframework.game.MarioGame;
import com.in28minute.learnspringframework.game.PacManGame;
import com.in28minute.learnspringframework.game.SuperContraGame;

public class App01GameBasicJava {
    public static void main(String[] args) {
        var marioGame = new MarioGame();
        var superContraGame = new SuperContraGame();
        var pacManGame = new PacManGame();

        var gameRunner = new GameRunner(pacManGame);

        gameRunner.run();
    }
}
