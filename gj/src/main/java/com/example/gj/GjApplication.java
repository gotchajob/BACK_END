package com.example.gj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication()
public class GjApplication {

	public static void main(String[] args) {
		SpringApplication.run(GjApplication.class, args);
	}

}
