package com.github;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage( "com.github" ) )
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfo("API REST Repo GitHub", 
				"API REST para listar todos os repositórios de um determinado usuário do GitHub.",
				"1.0.0", "", 
				new Contact("Vanildo vanni", "", "vanildov@br.ibm.com"), 
				"Apache License Version 2.0", 
				"https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}
}
