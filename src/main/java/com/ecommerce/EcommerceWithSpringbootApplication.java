package com.ecommerce;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class EcommerceWithSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceWithSpringbootApplication.class, args);
	}


	@Configuration
	public class OpenApiConfig {
		@Bean
		public OpenAPI openApi() {
			return new OpenAPI()
					.info(new Info()
							.title("Title")
							.description("my little API")
							.version("v1.0")
							.contact(new Contact()
									.name("Arun")
									.url("https://asbnotebook.com")
									.email("asbnotebook@gmail.com"))
							.termsOfService("TOC")
							.license(new License().name("License").url("#"))
					);
		}
	}
}
