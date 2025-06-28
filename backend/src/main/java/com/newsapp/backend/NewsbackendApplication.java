package com.newsapp.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.newsapp.backend")
public class NewsbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsbackendApplication.class, args);
	}

}
