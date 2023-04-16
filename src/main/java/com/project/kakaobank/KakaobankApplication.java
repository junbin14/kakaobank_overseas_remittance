package com.project.kakaobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KakaobankApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaobankApplication.class, args);
	}

}
