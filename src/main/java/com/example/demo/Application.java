package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.out.println(args[0] + "--" + args[1]);
        System.out.println("APP_NAME: " + System.getenv("APP_NAME"));
        SpringApplication.run(Application.class, args);
    }
}
