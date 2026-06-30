package it.ur3.siw.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
      return new OpenAPI()
          .info(new Info()
              .title("Puntopallone API")
              .version("v1")
              .description("API REST per la gestione di tornei amatoriali"));
  }
}
