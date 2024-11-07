package com.example.api_gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Demo Microservice Application",
                version = "1.0",
                contact = @Contact(
                        name = "Demo App Support Team",
                        url = "http://example.com",
                        email = "support@example.com"
                ),
                description = "API documentation for the Demo Microservice Application used to illustrate microservices deployment on AWS EKS.",
                termsOfService = "Terms of service: Use this demo API under the provided conditions as outlined in our documentation."
        )
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
