package com.profile.matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableConfigurationProperties
@EnableWebMvc
@EnableJpaRepositories
public class MatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcherApplication.class, args);
	}

}
