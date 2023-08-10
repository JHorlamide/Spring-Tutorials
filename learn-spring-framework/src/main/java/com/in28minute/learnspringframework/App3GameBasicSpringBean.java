package com.in28minute.learnspringframework;

import com.in28minute.learnspringframework.game.GameRunner;
import com.in28minute.learnspringframework.game.GamingConsole;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App3GameBasicSpringBean {
    public static void main(String[] args) {
        try(var context =
                    new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
            context.getBean(GamingConsole.class).up();
            context.getBean(GameRunner.class).run();
        }
    }
}
