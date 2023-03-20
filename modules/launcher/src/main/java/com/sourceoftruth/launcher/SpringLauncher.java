package com.sourceoftruth.launcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sourceoftruth")
public class SpringLauncher {
    public static void main(String... args) {
        SpringApplication.run(SpringLauncher.class, args);
    }
}
