package com.chariotit.uva.sc.qdsl;

import main.java.com.chariotit.uva.sc.qdsl.QLFrame;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

    public static void main(String [] args) {
        new SpringApplicationBuilder(Application.class)
                .headless(false)
                .web(false)
                .run(args);
    }


    /**
     * Creates the {@link QLFrame} object and returns it.
     *
     * This @Bean could have been replaced by a @Component annotation being
     * added to the {@link QLFrame} class.
     *
     * @return the application window
     */
    @Bean
    public QLFrame frame() {
        return new QLFrame();
    }
}
