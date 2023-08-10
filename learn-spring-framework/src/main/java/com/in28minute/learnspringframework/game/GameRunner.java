package com.in28minute.learnspringframework.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameRunner {
    private final GamingConsole game;

    public GameRunner(GamingConsole game) {
        this.game = game;
    }

    @Bean
    public void run() {
        System.out.println("Running game: " + game);

        game.up();
        game.down();
        game.left();
        game.right();
    }
}
