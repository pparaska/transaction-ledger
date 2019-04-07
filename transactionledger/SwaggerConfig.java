package com.blockchain.transactionledger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
// This annotation enables the Swagger support in the application.
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select().paths(regex("/blockchain.*")).build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Blockchain Application").description("API reference guide for developers").termsOfServiceUrl("https://www.javacodegeeks.com/").contact("Shubham Raut & Poonam Paraskar").version("1.0").build();	
	}

}
