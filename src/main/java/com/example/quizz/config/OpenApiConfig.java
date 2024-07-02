package com.example.quizz.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${open.api.title}")
    private String title;
    @Value("${open.api.version}")
    private String version;
    @Value("${open.api.description}")
    private String description;
    @Value("${open.api.serverURl}")
    private String serverUrl;
    @Value("${open.api.serverName}")
    private String serverName;
    private final String API_KEY = "Bearer Token";
    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI().info(
                new Info()
                        .title(title)
                        .version(version)
                        .description(description)
        );
        openAPI.components(
                new Components()
                        .addSecuritySchemes(
                                API_KEY,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                        )
        );
        openAPI.addSecurityItem(new SecurityRequirement().addList(API_KEY));

        return openAPI;
    }
}
