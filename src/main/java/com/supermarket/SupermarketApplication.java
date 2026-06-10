package com.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * Supermarket Management System - Backend API
 * 
 * This is the entry point of the Spring Boot application.
 * The @SpringBootApplication annotation enables:
 * - @Configuration
 * - @EnableAutoConfiguration
 * - @ComponentScan
 */
@SpringBootApplication
public class SupermarketApplication {

    /**
     * Main method to run the Spring Boot application
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(SupermarketApplication.class, args);
    }
}
