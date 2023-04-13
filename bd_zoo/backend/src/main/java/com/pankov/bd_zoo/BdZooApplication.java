package com.pankov.bd_zoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.IntStream;

@SpringBootApplication
public class BdZooApplication {

	//TODO: Добавить историю покупок
	//TODO: Добавить бизнес логику несовместимости животных в клетках
	//TODO: Добавить потомство
	//TODO: Добавить Болезни
	//TODO: Вакцины
	//TODO: Добавить реализацию кухни
	//TODO: Добавить сортировку еды в магазине

	public static void main(String[] args) {
		SpringApplication.run(BdZooApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000", "http://localhost")
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "UPDATE", "DELETE", "PUT");
			}
		};
	}

}
 