package com.jamanchi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JamanchiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JamanchiApplication.class, args);
	}

}
