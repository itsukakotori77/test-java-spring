package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @ComponentScan({"com.example"})
// @EntityScan("com.example.entities")
// @EnableJpaRepositories("com.example.repositories")
public class FakeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeBackendApplication.class, args);
	}

	@Bean
	public ModelMapper modelmapper(){
		return new ModelMapper();
	}

}
