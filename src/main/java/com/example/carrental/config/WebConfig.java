package com.example.carrental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Applies this rule to all your endpoints under /api
                .allowedOrigins("http://localhost:3000") // Trusts requests from your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows these HTTP actions
                .allowedHeaders("*") // Allows all headers in the request
                .allowCredentials(true); // Important for handling security cookies or tokens later
    }
}
