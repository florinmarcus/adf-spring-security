package com.redsam.adfsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class AdfsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdfsecurityApplication.class, args);
	}
}
