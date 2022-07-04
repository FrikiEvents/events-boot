package com.frikiteam.eventsboot.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Requests Microservice")
            .description("Requests microservice: Where employees make their requests to register their trips")
            .version("1.0.0")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
            .description("Requests Microservice developed with Java and Spring Boot"));
  }
}
