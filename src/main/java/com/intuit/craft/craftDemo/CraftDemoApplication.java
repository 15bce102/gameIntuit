package com.intuit.craft.craftDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CraftDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CraftDemoApplication.class, args);
    }
}