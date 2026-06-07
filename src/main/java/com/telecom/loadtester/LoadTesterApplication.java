package com.telecom.loadtester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LoadTesterApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                LoadTesterApplication.class,
                args
        );

        System.out.println(
                "========================================="
        );

        System.out.println(
                "Asterisk Load Testing Platform Started"
        );

        System.out.println(
                "Version : 1.0"
        );

        System.out.println(
                "Port : 8080"
        );

        System.out.println(
                "========================================="
        );
    }
}
