package com.turntabl.testsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TestSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSystemApplication.class, args);
	}

}
