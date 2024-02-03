package com.mc.innuce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class InnuceApplication {
	
	public static void main(String[] args) {

		SpringApplication.run(InnuceApplication.class, args);
		
	}

}