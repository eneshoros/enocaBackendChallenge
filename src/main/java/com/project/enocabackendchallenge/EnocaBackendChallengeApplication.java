package com.project.enocabackendchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EnocaBackendChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnocaBackendChallengeApplication.class, args);
	}

}
