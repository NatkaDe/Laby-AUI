package com.example.art_museum;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ArtMuseumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtMuseumApplication.class, args);
	}
	@Bean
	@Qualifier("authors")
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.rootUri("http://localhost:8082/authors")
				.build();
	}

}
