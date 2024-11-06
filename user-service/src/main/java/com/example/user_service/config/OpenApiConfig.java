package com.example.user_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private final OpenApiProperties openApiProperties;

    public OpenApiConfig(OpenApiProperties openApiProperties) {
        this.openApiProperties = openApiProperties;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        // Add servers from the YAML configuration
        if (openApiProperties.getServers() != null) {
            openApiProperties.getServers().forEach(s ->
                    openAPI.addServersItem(new Server().url(s.getUrl()).description(s.getDescription()))
            );
        }

        return openAPI;
    }
}

@Component
@ConfigurationProperties(prefix = "openapi")
class OpenApiProperties {
    private List<ServerProperties> servers;

    public List<ServerProperties> getServers() {
        return servers;
    }

    public void setServers(List<ServerProperties> servers) {
        this.servers = servers;
    }

    public static class ServerProperties {
        private String url;
        private String description;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
