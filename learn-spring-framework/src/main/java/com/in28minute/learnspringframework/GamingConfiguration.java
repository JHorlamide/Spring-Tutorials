package com.in28minute.learnspringframework;

import com.in28minute.learnspringframework.game.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GamingConfiguration {
    @Bean
    public GamingConsole pacmanGame() {
        return new PacManGame();
    }

    @Bean
    public GamingConsole marioGame() {
        return new MarioGame();
    }

    @Bean
    public GamingConsole superContraGame() {
        return new SuperContraGame();
    }

    @Bean
    public GameRunner gameRunner(GamingConsole marioGame) {
        return new GameRunner(marioGame);
    }
}
