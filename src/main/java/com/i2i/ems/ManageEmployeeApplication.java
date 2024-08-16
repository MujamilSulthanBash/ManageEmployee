package com.i2i.ems;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManageEmployeeApplication {

	private static final Logger logger = LogManager.getLogger(ManageEmployeeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ManageEmployeeApplication.class, args);
		logger.info("Application Started");
	}

}
