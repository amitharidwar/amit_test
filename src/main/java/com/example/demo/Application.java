package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {


        logger.trace("This is a TRACE message.");
        logger.debug("This is a DEBUG message.");
        logger.info("This is an INFO message.");
        logger.warn("This is a WARN message.");
        logger.error("This is an ERROR message.");

        System.out.println(args[0] + "--" + args[1]);
        System.out.println("APP_NAME: " + System.getenv("APP_NAME"));
        SpringApplication.run(Application.class, args);
    }
}


// Run via Command Line


// $ APP_NAME=test java -Dlog_dir=E:/logs -Dspring.profiles.active=local -jar target/demo-0.0.1-SNAPSHOT.jar --amit sumit
