package com.chariotit.uva.sc.qdsl;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GLApplication {

    public static void main(String [] args) {
        new SpringApplicationBuilder(GLApplication.class)
                .headless(false)
                .web(false)
                .run(args);
    }
}
