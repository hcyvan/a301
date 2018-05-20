package com.navy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class A301AppC {
	public static void main(String[] args) {
		SpringApplication.run(A301AppC.class, args);
	}
}
