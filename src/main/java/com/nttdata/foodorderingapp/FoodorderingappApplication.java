package com.nttdata.foodorderingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FoodorderingappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodorderingappApplication.class, args);
	}

}
