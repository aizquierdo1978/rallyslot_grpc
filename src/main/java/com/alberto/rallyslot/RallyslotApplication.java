package com.alberto.rallyslot;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class RallyslotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RallyslotApplication.class, args);
	}

}
