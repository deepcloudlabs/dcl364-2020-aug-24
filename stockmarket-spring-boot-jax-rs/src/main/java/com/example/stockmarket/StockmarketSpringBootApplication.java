package com.example.stockmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockmarketSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockmarketSpringBootApplication.class, args);
	}

}
