package com.example.api_gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder, @Value("${user.service.url}") String userServiceUrl,
									 @Value("${employee.service.url}") String employeeServiceUrl) {
		return builder
				.routes()
				// Route to user service APIs
				.route(r -> r.path("/user-service/v3/api-docs").and().method(HttpMethod.GET).uri(userServiceUrl))
				.route(r -> r.path("/api/v1/user/**").and().method(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)
						.uri(userServiceUrl))

				// Route to employee service APIs
				.route(r -> r.path("/employee-service/v3/api-docs").and().method(HttpMethod.GET).uri(employeeServiceUrl))
				.route(r -> r.path("/api/v1/employee/**").and().method(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)
						.uri(employeeServiceUrl))
				.build();
	}

}
