package com.example.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.tictactoe", "com.example.tictactoe.controllers"})
@EnableScheduling
public class TictactoeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

}
