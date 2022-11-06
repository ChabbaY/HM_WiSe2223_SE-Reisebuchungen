package com.chabbay.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class
 *
 * @author Linus Englert
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.chabbay"})
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}