package es.unir.middleware.documentation;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Solución Middleware API_Rest").version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi httpApi() {
    	//Se podrá consultar la documentación en http://localhost:8080/v3/api-docs y en http://localhost:8080/v3/api-docs.yaml
        return GroupedOpenApi.builder()
                .group("http")
                .pathsToMatch("/**")
                .build();
    }
}
