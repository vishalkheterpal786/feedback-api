package com.study.feedback_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FeedbackApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeedbackApiApplication.class, args);
	}

}
