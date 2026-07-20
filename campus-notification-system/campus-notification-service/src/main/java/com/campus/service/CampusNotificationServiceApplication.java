package com.campus.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.campus.service", "com.campus.logging"})
@EnableScheduling
public class CampusNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusNotificationServiceApplication.class, args);
	}

}
