package com.jyj.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class IotHomeConsoleApp {
    public static void main(String[] args) {

        ApplicationContext app = SpringApplication.run(IotHomeConsoleApp.class, args);
    }
}