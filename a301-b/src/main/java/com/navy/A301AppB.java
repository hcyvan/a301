package com.navy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class A301AppB {
	public static void main(String[] args) {
		SpringApplication.run(A301AppB.class, args);
	}
}
